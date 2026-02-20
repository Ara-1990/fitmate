package com.the.fitmate.data


import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [ExerciseEntity::class, BicepsBrestEntity::class, TricepsBackEntity::class, ShoulderLegsEntity::class],
    version = 2, exportSchema = true
)

abstract class AppDb : RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao


    companion object {
        @Volatile
        private var INSTANCE: AppDb? = null

        fun getDatabase(context: Context): AppDb {
            return INSTANCE ?: synchronized(this) {

                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDb::class.java,
                    "fitness_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance


                CoroutineScope(Dispatchers.IO).launch {
                    val dao = instance.exerciseDao()


                    if (dao.getAll().isEmpty()) {
                        dao.insert(getPredefinedExercises(context))

                    }

                    if (dao.getAnyBicBrestCount() == 0) {
                        dao.insertBicBrest(getPredefinedBicepsBrestEntity(context))

                    }

                    if (dao.getAnyTricepsBackCount() == 0) {
                        dao.insertTricepsBack(getPredefinedTricepsBackEntity(context))

                    }

                    if (dao.getAnyShoulderLegsCount() == 0) {
                        dao.insertSholderLegs(getPradeFinedSoulderLegsEntity(context))

                    }
                }

                instance
            }
        }


        private fun getPredefinedExercises(context: Context): List<ExerciseEntity> {
            return listOf(
                ExerciseEntity(
                    name = "Concentration Curl dumbbell",
                    muscleGroup = "Biceps",
                    imagePath = "concentration_curl2"
                ),
                ExerciseEntity(
                    name = "Cable Rope Triceps Pushdown",
                    muscleGroup = "Triceps",
                    imagePath = "cable_rope_triceps_pushdown"
                ),
                ExerciseEntity(
                    name = "Chest Fly Machine",
                    muscleGroup = "Breast",
                    imagePath = "push_up"
                ),
                ExerciseEntity(
                    name = "Pull-Up",
                    muscleGroup = "back",
                    imagePath = "pull_up"
                ),
                ExerciseEntity(
                    name = "Dumbbell Lateral Raise",
                    muscleGroup = "shoulder",
                    imagePath = "dumbbell_lateral_raise"
                ),

                ExerciseEntity(
                    name = "Leg Press",
                    muscleGroup = "legs",
                    imagePath = "leg_press"
                )
            )
        }




        private fun getPredefinedBicepsBrestEntity(context: Context): List<BicepsBrestEntity> {
            return listOf(
                BicepsBrestEntity(
                    name = "Concentration Curl",
                    muscleGroup = "Biceps",
                    imagePath = "concentration_curl2",
                    description = "Sit on a flat bench with your legs spread apart.\n" +
                            "\n" +
                            "Hold a dumbbell in one hand with an underhand (supinated) grip — palm facing upward.\n" +
                            "\n" +
                            "Lean forward slightly and rest your working arm’s elbow on the inner thigh of the same side leg.\n" +
                            "\n" +
                            "Let the dumbbell hang down naturally toward the floor, with your arm fully extended.\n",
                    weight = "10kg",
                    numbers = "10",
                    sets = "3",
                    week = 1
                ),
                BicepsBrestEntity(
                    name = "Concentration Curl",
                    muscleGroup = "Biceps",
                    imagePath = "concentration_curl",
                    description = "Sit on a flat bench with your feet spread apart and your chest slightly leaning forward.\n" +
                            "\n" +
                            "Hold a dumbbell in one hand (as shown) with an underhand grip (palm facing upward).\n" +
                            "\n" +
                            "Rest your elbow on the inner thigh of the same side leg, just above the knee — this stabilizes the arm.\n" +
                            "\n" +
                            "Let the dumbbell hang straight down toward the floor with your arm fully extended, keeping the wrist straight.\n",
                    weight = "10kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),
                BicepsBrestEntity(
                    name = "Seated Barbell Curl",
                    muscleGroup = "Biceps",
                    imagePath = "seated_barbell_curl",
                    description = "\n" +
                            "Sit on a flat bench with your back straight and feet flat on the ground.\n" +
                            "\n" +
                            "Hold the EZ curl bar (or straight barbell) with an underhand (supinated) grip, shoulder-width apart.\n" +
                            "\n" +
                            "Keep your elbows close to your torso and your upper arms stationary.\n",
                    weight = "10kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),
                BicepsBrestEntity(
                    name = "Preacher Curl with Dumbbells",
                    muscleGroup = "Biceps",
                    imagePath = "preacher_curl_with_dumbbells",
                    description = "\n" +
                            "Sit on a preacher bench (the slanted pad that supports your upper arms).\n" +
                            "\n" +
                            "Hold a dumbbell in each hand using an underhand grip (palms facing up).\n" +
                            "\n" +
                            "Rest your upper arms completely on the pad; only your forearms should move.\n",
                    weight = "10kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),
                BicepsBrestEntity(
                    name = "Incline Barbell Bench Press",
                    muscleGroup = "brest",
                    imagePath = "incline_barbell_bench_press",
                    description = "\n" +
                            "Set an adjustable bench to an incline of about 30–45°.\n" +
                            "\n" +
                            "Lie back with your feet flat on the floor and your head, shoulders, and hips in contact with the bench.\n" +
                            "\n" +
                            "Grip the bar slightly wider than shoulder width (about 1.5x your shoulder width).\n" +
                            "\n" +
                            "Unrack the bar carefully so it’s positioned directly above your upper chest.\n",
                    weight = "40kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),

                BicepsBrestEntity(
                    name = "Incline Dumbbell Press",
                    muscleGroup = "brest",
                    imagePath = "incline_dumbbell_press",
                    description = "\n" +
                            "Adjust a bench to a 30–45° incline.\n" +
                            "\n" +
                            "Sit down holding a dumbbell in each hand on your thighs.\n" +
                            "\n" +
                            "Lie back and use your legs to help lift the dumbbells into position at shoulder level.\n" +
                            "\n" +
                            "Keep your feet flat on the floor and your core tight.\n" +
                            "\n" +
                            "Your palms should face forward and your elbows slightly below shoulder height.\n",
                    weight = "10kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),

                BicepsBrestEntity(
                    name = "Push Up",
                    muscleGroup = "brest",
                    imagePath = "push_up",
                    description = "\n" +
                            "Get into a plank position: place your hands slightly wider than shoulder-width apart on the floor.\n" +
                            "\n" +
                            "Keep your legs extended and your toes on the ground.\n" +
                            "\n" +
                            "Your body should form a straight line from your head to your heels.\n" +
                            "\n" +
                            "Engage your core and keep your neck neutral — look slightly forward, not down.\n",
                    weight = "",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),

                BicepsBrestEntity(
                    name = "Chest Fly Machine",
                    muscleGroup = "brest",
                    imagePath = "chest_fly_machine",
                    description = "Sit on the pec deck machine and adjust the seat height so that the handles are at chest level.\n" +
                            "\n" +
                            "Place your back firmly against the pad.\n" +
                            "\n" +
                            "Grab the handles with a neutral or pronated grip (palms facing forward or slightly inward).\n" +
                            "\n" +
                            "Keep your feet flat on the floor and core engaged.\n" +
                            "\n" +
                            "Slightly bend your elbows (about 10–15°) — this bend remains fixed during the entire movement.\n",
                    weight = "35kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),



                BicepsBrestEntity(
                    name = "Concentration Curl",
                    muscleGroup = "Biceps",
                    imagePath = "concentration_curl2",
                    description = "Sit on a flat bench with your legs spread apart.\n" +
                            "\n" +
                            "Hold a dumbbell in one hand with an underhand (supinated) grip — palm facing upward.\n" +
                            "\n" +
                            "Lean forward slightly and rest your working arm’s elbow on the inner thigh of the same side leg.\n" +
                            "\n" +
                            "Let the dumbbell hang down naturally toward the floor, with your arm fully extended.\n",
                    weight = "12kg",
                    numbers = "12",
                    sets = "4",
                    week = 2
                ),
                BicepsBrestEntity(
                    name = "Concentration Curl",
                    muscleGroup = "Biceps",
                    imagePath = "concentration_curl",
                    description = "Sit on a flat bench with your feet spread apart and your chest slightly leaning forward.\n" +
                            "\n" +
                            "Hold a dumbbell in one hand (as shown) with an underhand grip (palm facing upward).\n" +
                            "\n" +
                            "Rest your elbow on the inner thigh of the same side leg, just above the knee — this stabilizes the arm.\n" +
                            "\n" +
                            "Let the dumbbell hang straight down toward the floor with your arm fully extended, keeping the wrist straight.\n",
                    weight = "12kg",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),
                BicepsBrestEntity(
                    name = "Seated Barbell Curl",
                    muscleGroup = "Biceps",
                    imagePath = "seated_barbell_curl",
                    description = "\n" +
                            "Sit on a flat bench with your back straight and feet flat on the ground.\n" +
                            "\n" +
                            "Hold the EZ curl bar (or straight barbell) with an underhand (supinated) grip, shoulder-width apart.\n" +
                            "\n" +
                            "Keep your elbows close to your torso and your upper arms stationary.\n",
                    weight = "12kg",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),
                BicepsBrestEntity(
                    name = "Preacher Curl with Dumbbells",
                    muscleGroup = "Biceps",
                    imagePath = "preacher_curl_with_dumbbells",
                    description = "\n" +
                            "Sit on a preacher bench (the slanted pad that supports your upper arms).\n" +
                            "\n" +
                            "Hold a dumbbell in each hand using an underhand grip (palms facing up).\n" +
                            "\n" +
                            "Rest your upper arms completely on the pad; only your forearms should move.\n",
                    weight = "12kg",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),
                BicepsBrestEntity(
                    name = "Incline Barbell Bench Press",
                    muscleGroup = "brest",
                    imagePath = "incline_barbell_bench_press",
                    description = "\n" +
                            "Set an adjustable bench to an incline of about 30–45°.\n" +
                            "\n" +
                            "Lie back with your feet flat on the floor and your head, shoulders, and hips in contact with the bench.\n" +
                            "\n" +
                            "Grip the bar slightly wider than shoulder width (about 1.5x your shoulder width).\n" +
                            "\n" +
                            "Unrack the bar carefully so it’s positioned directly above your upper chest.\n",
                    weight = "40kg",
                    numbers = "10",
                    sets = "4",
                    week = 2

                ),

                BicepsBrestEntity(
                    name = "Incline Dumbbell Press",
                    muscleGroup = "brest",
                    imagePath = "incline_dumbbell_press",
                    description = "\n" +
                            "Adjust a bench to a 30–45° incline.\n" +
                            "\n" +
                            "Sit down holding a dumbbell in each hand on your thighs.\n" +
                            "\n" +
                            "Lie back and use your legs to help lift the dumbbells into position at shoulder level.\n" +
                            "\n" +
                            "Keep your feet flat on the floor and your core tight.\n" +
                            "\n" +
                            "Your palms should face forward and your elbows slightly below shoulder height.\n",
                    weight = "12kg",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),

                BicepsBrestEntity(
                    name = "Push Up",
                    muscleGroup = "brest",
                    imagePath = "push_up",
                    description = "\n" +
                            "Get into a plank position: place your hands slightly wider than shoulder-width apart on the floor.\n" +
                            "\n" +
                            "Keep your legs extended and your toes on the ground.\n" +
                            "\n" +
                            "Your body should form a straight line from your head to your heels.\n" +
                            "\n" +
                            "Engage your core and keep your neck neutral — look slightly forward, not down.\n",
                    weight = "",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),

                BicepsBrestEntity(
                    name = "Chest Fly Machine",
                    muscleGroup = "brest",
                    imagePath = "chest_fly_machine",
                    description = "Sit on the pec deck machine and adjust the seat height so that the handles are at chest level.\n" +
                            "\n" +
                            "Place your back firmly against the pad.\n" +
                            "\n" +
                            "Grab the handles with a neutral or pronated grip (palms facing forward or slightly inward).\n" +
                            "\n" +
                            "Keep your feet flat on the floor and core engaged.\n" +
                            "\n" +
                            "Slightly bend your elbows (about 10–15°) — this bend remains fixed during the entire movement.\n",
                    weight = "40kg",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),




                BicepsBrestEntity(
                    name = "Concentration Curl",
                    muscleGroup = "Biceps",
                    imagePath = "concentration_curl2",
                    description = "Sit on a flat bench with your legs spread apart.\n" +
                            "\n" +
                            "Hold a dumbbell in one hand with an underhand (supinated) grip — palm facing upward.\n" +
                            "\n" +
                            "Lean forward slightly and rest your working arm’s elbow on the inner thigh of the same side leg.\n" +
                            "\n" +
                            "Let the dumbbell hang down naturally toward the floor, with your arm fully extended.\n",
                    weight = "14kg",
                    numbers = "12",
                    sets = "5",
                    week = 3
                ),
                BicepsBrestEntity(
                    name = "Concentration Curl",
                    muscleGroup = "Biceps",
                    imagePath = "concentration_curl",
                    description = "Sit on a flat bench with your feet spread apart and your chest slightly leaning forward.\n" +
                            "\n" +
                            "Hold a dumbbell in one hand (as shown) with an underhand grip (palm facing upward).\n" +
                            "\n" +
                            "Rest your elbow on the inner thigh of the same side leg, just above the knee — this stabilizes the arm.\n" +
                            "\n" +
                            "Let the dumbbell hang straight down toward the floor with your arm fully extended, keeping the wrist straight.\n",
                    weight = "14kg",
                    numbers = "12",
                    sets = "5",
                    week = 3

                ),
                BicepsBrestEntity(
                    name = "Seated Barbell Curl",
                    muscleGroup = "Biceps",
                    imagePath = "seated_barbell_curl",
                    description = "\n" +
                            "Sit on a flat bench with your back straight and feet flat on the ground.\n" +
                            "\n" +
                            "Hold the EZ curl bar (or straight barbell) with an underhand (supinated) grip, shoulder-width apart.\n" +
                            "\n" +
                            "Keep your elbows close to your torso and your upper arms stationary.\n",
                    weight = "14kg",
                    numbers = "12",
                    sets = "5",
                    week = 3

                ),
                BicepsBrestEntity(
                    name = "Preacher Curl with Dumbbells",
                    muscleGroup = "Biceps",
                    imagePath = "preacher_curl_with_dumbbells",
                    description = "\n" +
                            "Sit on a preacher bench (the slanted pad that supports your upper arms).\n" +
                            "\n" +
                            "Hold a dumbbell in each hand using an underhand grip (palms facing up).\n" +
                            "\n" +
                            "Rest your upper arms completely on the pad; only your forearms should move.\n",
                    weight = "14kg",
                    numbers = "12",
                    sets = "5",
                    week = 3

                ),
                BicepsBrestEntity(
                    name = "Incline Barbell Bench Press",
                    muscleGroup = "brest",
                    imagePath = "incline_barbell_bench_press",
                    description = "\n" +
                            "Set an adjustable bench to an incline of about 30–45°.\n" +
                            "\n" +
                            "Lie back with your feet flat on the floor and your head, shoulders, and hips in contact with the bench.\n" +
                            "\n" +
                            "Grip the bar slightly wider than shoulder width (about 1.5x your shoulder width).\n" +
                            "\n" +
                            "Unrack the bar carefully so it’s positioned directly above your upper chest.\n",
                    weight = "45kg",
                    numbers = "10",
                    sets = "4",
                    week = 3

                ),

                BicepsBrestEntity(
                    name = "Incline Dumbbell Press",
                    muscleGroup = "brest",
                    imagePath = "incline_dumbbell_press",
                    description = "\n" +
                            "Adjust a bench to a 30–45° incline.\n" +
                            "\n" +
                            "Sit down holding a dumbbell in each hand on your thighs.\n" +
                            "\n" +
                            "Lie back and use your legs to help lift the dumbbells into position at shoulder level.\n" +
                            "\n" +
                            "Keep your feet flat on the floor and your core tight.\n" +
                            "\n" +
                            "Your palms should face forward and your elbows slightly below shoulder height.\n",
                    weight = "14kg",
                    numbers = "12",
                    sets = "5",
                    week = 3

                ),

                BicepsBrestEntity(
                    name = "Push Up",
                    muscleGroup = "brest",
                    imagePath = "push_up",
                    description = "\n" +
                            "Get into a plank position: place your hands slightly wider than shoulder-width apart on the floor.\n" +
                            "\n" +
                            "Keep your legs extended and your toes on the ground.\n" +
                            "\n" +
                            "Your body should form a straight line from your head to your heels.\n" +
                            "\n" +
                            "Engage your core and keep your neck neutral — look slightly forward, not down.\n",
                    weight = "",
                    numbers = "14",
                    sets = "5",
                    week = 3

                ),

                BicepsBrestEntity(
                    name = "Chest Fly Machine",
                    muscleGroup = "brest",
                    imagePath = "chest_fly_machine",
                    description = "Sit on the pec deck machine and adjust the seat height so that the handles are at chest level.\n" +
                            "\n" +
                            "Place your back firmly against the pad.\n" +
                            "\n" +
                            "Grab the handles with a neutral or pronated grip (palms facing forward or slightly inward).\n" +
                            "\n" +
                            "Keep your feet flat on the floor and core engaged.\n" +
                            "\n" +
                            "Slightly bend your elbows (about 10–15°) — this bend remains fixed during the entire movement.\n",
                    weight = "45kg",
                    numbers = "14",
                    sets = "5",
                    week = 3

                ),




                BicepsBrestEntity(
                    name = "Concentration Curl",
                    muscleGroup = "Biceps",
                    imagePath = "concentration_curl2",
                    description = "Sit on a flat bench with your legs spread apart.\n" +
                            "\n" +
                            "Hold a dumbbell in one hand with an underhand (supinated) grip — palm facing upward.\n" +
                            "\n" +
                            "Lean forward slightly and rest your working arm’s elbow on the inner thigh of the same side leg.\n" +
                            "\n" +
                            "Let the dumbbell hang down naturally toward the floor, with your arm fully extended.\n",
                    weight = "16kg",
                    numbers = "10",
                    sets = "4",
                    week = 4
                ),
                BicepsBrestEntity(
                    name = "Concentration Curl",
                    muscleGroup = "Biceps",
                    imagePath = "concentration_curl",
                    description = "Sit on a flat bench with your feet spread apart and your chest slightly leaning forward.\n" +
                            "\n" +
                            "Hold a dumbbell in one hand (as shown) with an underhand grip (palm facing upward).\n" +
                            "\n" +
                            "Rest your elbow on the inner thigh of the same side leg, just above the knee — this stabilizes the arm.\n" +
                            "\n" +
                            "Let the dumbbell hang straight down toward the floor with your arm fully extended, keeping the wrist straight.\n",
                    weight = "16kg",
                    numbers = "10",
                    sets = "4",
                    week = 4

                ),
                BicepsBrestEntity(
                    name = "Seated Barbell Curl",
                    muscleGroup = "Biceps",
                    imagePath = "seated_barbell_curl",
                    description = "\n" +
                            "Sit on a flat bench with your back straight and feet flat on the ground.\n" +
                            "\n" +
                            "Hold the EZ curl bar (or straight barbell) with an underhand (supinated) grip, shoulder-width apart.\n" +
                            "\n" +
                            "Keep your elbows close to your torso and your upper arms stationary.\n",
                    weight = "16kg",
                    numbers = "10",
                    sets = "4",
                    week = 4

                ),
                BicepsBrestEntity(
                    name = "Preacher Curl with Dumbbells",
                    muscleGroup = "Biceps",
                    imagePath = "preacher_curl_with_dumbbells",
                    description = "\n" +
                            "Sit on a preacher bench (the slanted pad that supports your upper arms).\n" +
                            "\n" +
                            "Hold a dumbbell in each hand using an underhand grip (palms facing up).\n" +
                            "\n" +
                            "Rest your upper arms completely on the pad; only your forearms should move.\n",
                    weight = "16kg",
                    numbers = "10",
                    sets = "4",
                    week = 4

                ),
                BicepsBrestEntity(
                    name = "Incline Barbell Bench Press",
                    muscleGroup = "brest",
                    imagePath = "incline_barbell_bench_press",
                    description = "\n" +
                            "Set an adjustable bench to an incline of about 30–45°.\n" +
                            "\n" +
                            "Lie back with your feet flat on the floor and your head, shoulders, and hips in contact with the bench.\n" +
                            "\n" +
                            "Grip the bar slightly wider than shoulder width (about 1.5x your shoulder width).\n" +
                            "\n" +
                            "Unrack the bar carefully so it’s positioned directly above your upper chest.\n",
                    weight = "50kg",
                    numbers = "10",
                    sets = "4",
                    week = 4

                ),

                BicepsBrestEntity(
                    name = "Incline Dumbbell Press",
                    muscleGroup = "brest",
                    imagePath = "incline_dumbbell_press",
                    description = "\n" +
                            "Adjust a bench to a 30–45° incline.\n" +
                            "\n" +
                            "Sit down holding a dumbbell in each hand on your thighs.\n" +
                            "\n" +
                            "Lie back and use your legs to help lift the dumbbells into position at shoulder level.\n" +
                            "\n" +
                            "Keep your feet flat on the floor and your core tight.\n" +
                            "\n" +
                            "Your palms should face forward and your elbows slightly below shoulder height.\n",
                    weight = "16kg",
                    numbers = "10",
                    sets = "4",
                    week = 4

                ),

                BicepsBrestEntity(
                    name = "Push Up",
                    muscleGroup = "brest",
                    imagePath = "push_up",
                    description = "\n" +
                            "Get into a plank position: place your hands slightly wider than shoulder-width apart on the floor.\n" +
                            "\n" +
                            "Keep your legs extended and your toes on the ground.\n" +
                            "\n" +
                            "Your body should form a straight line from your head to your heels.\n" +
                            "\n" +
                            "Engage your core and keep your neck neutral — look slightly forward, not down.\n",
                    weight = "",
                    numbers = "16",
                    sets = "5",
                    week = 4

                ),

                BicepsBrestEntity(
                    name = "Chest Fly Machine",
                    muscleGroup = "brest",
                    imagePath = "chest_fly_machine",
                    description = "Sit on the pec deck machine and adjust the seat height so that the handles are at chest level.\n" +
                            "\n" +
                            "Place your back firmly against the pad.\n" +
                            "\n" +
                            "Grab the handles with a neutral or pronated grip (palms facing forward or slightly inward).\n" +
                            "\n" +
                            "Keep your feet flat on the floor and core engaged.\n" +
                            "\n" +
                            "Slightly bend your elbows (about 10–15°) — this bend remains fixed during the entire movement.\n",
                    weight = "50kg",
                    numbers = "12",
                    sets = "4",
                    week = 4

                ),

                )


        }



        private fun getPredefinedTricepsBackEntity(context: Context): List<TricepsBackEntity> {
            return listOf(
                TricepsBackEntity(
                    name = "Rope Triceps Pushdown",
                    muscleGroup = "Triceps",
                    imagePath = "rope_triceps_pushdown",
                    description = "\n" +
                            "Attach a rope handle to the high pulley of a cable machine.\n" +
                            "\n" +
                            "Sit or stand (as shown in the image — seated variation helps with stability).\n" +
                            "\n" +
                            "Grasp the rope with a neutral grip (palms facing each other).\n" +
                            "\n" +
                            "Keep your elbows close to your torso and bent at about 90°.\n" +
                            "\n" +
                            "Maintain an upright posture with your chest up and shoulders back.\n",
                    weight = "12kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),
                TricepsBackEntity(
                    name = "Overhead Cable Triceps",
                    muscleGroup = "Triceps",
                    imagePath = "overhead_cable_triceps",
                    description = "\n" +
                            "Sit on a bench with back support in front of a low pulley cable machine.\n" +
                            "\n" +
                            "Attach a rope handle (or single handle) to the low pulley.\n" +
                            "\n" +
                            "Grab the handle with both hands and bring it overhead so your arms are bent and elbows pointing up, as shown in the photo.\n" +
                            "\n" +
                            "Keep your core tight and back straight.\n",
                    weight = "10kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),
                TricepsBackEntity(
                    name = "Cable Rope Triceps Pushdown",
                    muscleGroup = "Triceps",
                    imagePath = "cable_rope_triceps_pushdown",
                    description = "\n" +
                            "Attach a rope handle to the high pulley of a cable machine.\n" +
                            "\n" +
                            "Stand upright, facing the machine.\n" +
                            "\n" +
                            "Grasp the rope with a neutral grip (palms facing each other).\n" +
                            "\n" +
                            "Keep your elbows close to your body and your forearms parallel to the floor.\n" +
                            "\n" +
                            "Slightly bend your knees and keep your core tight for stability.\n",
                    weight = "10kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),

                TricepsBackEntity(
                    name = "Seated Overhead Dumbbell ",
                    muscleGroup = "Triceps",
                    imagePath = "seated_overhead_dumbbell",
                    description = "\n" +
                            "Sit on a bench with back support to keep your torso stable.\n" +
                            "\n" +
                            "Hold one dumbbell with both hands, placing your palms against the inner plate or handle (as shown).\n" +
                            "\n" +
                            "Raise the dumbbell over your head until your arms are fully extended — this is the starting position.\n" +
                            "\n" +
                            "Keep your elbows close to your head and upper arms vertical (don’t flare out).\n" +
                            "\n" +
                            "Engage your core to prevent arching your lower back.\n",
                    weight = "12kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),

                TricepsBackEntity(
                    name = "Pull-Up",
                    muscleGroup = "Back",
                    imagePath = "pull_up",
                    description = "\n" +
                            "Grasp a pull-up bar with a pronated (overhand) grip, hands slightly wider than shoulder-width apart.\n" +
                            "\n" +
                            "Hang from the bar with your arms fully extended, shoulders down, and core engaged.\n" +
                            "\n" +
                            "Keep your legs straight or slightly bent (crossed at the ankles if preferred).\n",
                    weight = "",
                    numbers = "7",
                    sets = "3",
                    week = 1

                ),

                TricepsBackEntity(
                    name = "Bent-Over Barbell Row",
                    muscleGroup = "Back",
                    imagePath = "bent_over_barbell_row",
                    description = "\n" +
                            "Stand with your feet shoulder-width apart and grasp a barbell with an overhand (pronated) grip.\n" +
                            "\n" +
                            "Slightly bend your knees and hinge forward at the hips, keeping your back straight and your core tight.\n" +
                            "\n" +
                            "The bar should hang just below your knees, your torso leaning forward at about a 45° angle.\n" +
                            "\n" +
                            "Keep your head in line with your spine — look slightly ahead, not up or down.\n",
                    weight = "12kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),

                TricepsBackEntity(
                    name = "Lat Pulldown",
                    muscleGroup = "Back",
                    imagePath = "lat_pulldown",
                    description = "\n" +
                            "Sit on the lat pulldown machine and adjust the thigh pad so it fits snugly against your thighs to keep your body stable.\n" +
                            "\n" +
                            "Grasp the bar with a wide overhand grip (palms facing away) — your hands should be slightly wider than shoulder-width apart.\n" +
                            "\n" +
                            "Sit upright with a straight back, chest lifted, and shoulders down and back.\n" +
                            "\n" +
                            "Extend your arms fully so that you feel a stretch in your lats before starting.\n",
                    weight = "15kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),

                TricepsBackEntity(
                    name = "One-Arm Dumbbell Row",
                    muscleGroup = "Back",
                    imagePath = "one_arm_dumbbell_row",
                    description = "\n" +
                            "Place one knee and the same-side hand on a flat bench for support.\n" +
                            "\n" +
                            "Keep your back flat, core tight, and your head aligned with your spine.\n" +
                            "\n" +
                            "Hold a dumbbell in the opposite hand, letting it hang straight down toward the floor with your arm fully extended.\n" +
                            "\n" +
                            "Your shoulders should be square to the ground — don’t twist your torso.\n",
                    weight = "10kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),




                TricepsBackEntity(
                    name = "Rope Triceps Pushdown",
                    muscleGroup = "Triceps",
                    imagePath = "rope_triceps_pushdown",
                    description = "\n" +
                            "Attach a rope handle to the high pulley of a cable machine.\n" +
                            "\n" +
                            "Sit or stand (as shown in the image — seated variation helps with stability).\n" +
                            "\n" +
                            "Grasp the rope with a neutral grip (palms facing each other).\n" +
                            "\n" +
                            "Keep your elbows close to your torso and bent at about 90°.\n" +
                            "\n" +
                            "Maintain an upright posture with your chest up and shoulders back.\n",
                    weight = "12kg",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),
                TricepsBackEntity(
                    name = "Overhead Cable Triceps",
                    muscleGroup = "Triceps",
                    imagePath = "overhead_cable_triceps",
                    description = "\n" +
                            "Sit on a bench with back support in front of a low pulley cable machine.\n" +
                            "\n" +
                            "Attach a rope handle (or single handle) to the low pulley.\n" +
                            "\n" +
                            "Grab the handle with both hands and bring it overhead so your arms are bent and elbows pointing up, as shown in the photo.\n" +
                            "\n" +
                            "Keep your core tight and back straight.\n",
                    weight = "10kg",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),
                TricepsBackEntity(
                    name = "Cable Rope Triceps Pushdown",
                    muscleGroup = "Triceps",
                    imagePath = "cable_rope_triceps_pushdown",
                    description = "\n" +
                            "Attach a rope handle to the high pulley of a cable machine.\n" +
                            "\n" +
                            "Stand upright, facing the machine.\n" +
                            "\n" +
                            "Grasp the rope with a neutral grip (palms facing each other).\n" +
                            "\n" +
                            "Keep your elbows close to your body and your forearms parallel to the floor.\n" +
                            "\n" +
                            "Slightly bend your knees and keep your core tight for stability.\n",
                    weight = "10kg",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),

                TricepsBackEntity(
                    name = "Seated Overhead Dumbbell ",
                    muscleGroup = "Triceps",
                    imagePath = "seated_overhead_dumbbell",
                    description = "\n" +
                            "Sit on a bench with back support to keep your torso stable.\n" +
                            "\n" +
                            "Hold one dumbbell with both hands, placing your palms against the inner plate or handle (as shown).\n" +
                            "\n" +
                            "Raise the dumbbell over your head until your arms are fully extended — this is the starting position.\n" +
                            "\n" +
                            "Keep your elbows close to your head and upper arms vertical (don’t flare out).\n" +
                            "\n" +
                            "Engage your core to prevent arching your lower back.\n",
                    weight = "12kg",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),

                TricepsBackEntity(
                    name = "Pull-Up",
                    muscleGroup = "Back",
                    imagePath = "pull_up",
                    description = "\n" +
                            "Grasp a pull-up bar with a pronated (overhand) grip, hands slightly wider than shoulder-width apart.\n" +
                            "\n" +
                            "Hang from the bar with your arms fully extended, shoulders down, and core engaged.\n" +
                            "\n" +
                            "Keep your legs straight or slightly bent (crossed at the ankles if preferred).\n",
                    weight = "",
                    numbers = "10",
                    sets = "3",
                    week = 2

                ),

                TricepsBackEntity(
                    name = "Bent-Over Barbell Row",
                    muscleGroup = "Back",
                    imagePath = "bent_over_barbell_row",
                    description = "\n" +
                            "Stand with your feet shoulder-width apart and grasp a barbell with an overhand (pronated) grip.\n" +
                            "\n" +
                            "Slightly bend your knees and hinge forward at the hips, keeping your back straight and your core tight.\n" +
                            "\n" +
                            "The bar should hang just below your knees, your torso leaning forward at about a 45° angle.\n" +
                            "\n" +
                            "Keep your head in line with your spine — look slightly ahead, not up or down.\n",
                    weight = "12kg",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),

                TricepsBackEntity(
                    name = "Lat Pulldown",
                    muscleGroup = "Back",
                    imagePath = "lat_pulldown",
                    description = "\n" +
                            "Sit on the lat pulldown machine and adjust the thigh pad so it fits snugly against your thighs to keep your body stable.\n" +
                            "\n" +
                            "Grasp the bar with a wide overhand grip (palms facing away) — your hands should be slightly wider than shoulder-width apart.\n" +
                            "\n" +
                            "Sit upright with a straight back, chest lifted, and shoulders down and back.\n" +
                            "\n" +
                            "Extend your arms fully so that you feel a stretch in your lats before starting.\n",
                    weight = "15kg",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),

                TricepsBackEntity(
                    name = "One-Arm Dumbbell Row",
                    muscleGroup = "Back",
                    imagePath = "one_arm_dumbbell_row",
                    description = "\n" +
                            "Place one knee and the same-side hand on a flat bench for support.\n" +
                            "\n" +
                            "Keep your back flat, core tight, and your head aligned with your spine.\n" +
                            "\n" +
                            "Hold a dumbbell in the opposite hand, letting it hang straight down toward the floor with your arm fully extended.\n" +
                            "\n" +
                            "Your shoulders should be square to the ground — don’t twist your torso.\n",
                    weight = "10kg",
                    numbers = "12",
                    sets = "4",
                    week = 2


                ),




                TricepsBackEntity(
                    name = "Rope Triceps Pushdown",
                    muscleGroup = "Triceps",
                    imagePath = "rope_triceps_pushdown",
                    description = "\n" +
                            "Attach a rope handle to the high pulley of a cable machine.\n" +
                            "\n" +
                            "Sit or stand (as shown in the image — seated variation helps with stability).\n" +
                            "\n" +
                            "Grasp the rope with a neutral grip (palms facing each other).\n" +
                            "\n" +
                            "Keep your elbows close to your torso and bent at about 90°.\n" +
                            "\n" +
                            "Maintain an upright posture with your chest up and shoulders back.\n",
                    weight = "14kg",
                    numbers = "12",
                    sets = "4",
                    week = 3

                ),
                TricepsBackEntity(
                    name = "Overhead Cable Triceps",
                    muscleGroup = "Triceps",
                    imagePath = "overhead_cable_triceps",
                    description = "\n" +
                            "Sit on a bench with back support in front of a low pulley cable machine.\n" +
                            "\n" +
                            "Attach a rope handle (or single handle) to the low pulley.\n" +
                            "\n" +
                            "Grab the handle with both hands and bring it overhead so your arms are bent and elbows pointing up, as shown in the photo.\n" +
                            "\n" +
                            "Keep your core tight and back straight.\n",
                    weight = "12kg",
                    numbers = "12",
                    sets = "4",
                    week = 3

                ),
                TricepsBackEntity(
                    name = "Cable Rope Triceps Pushdown",
                    muscleGroup = "Triceps",
                    imagePath = "cable_rope_triceps_pushdown",
                    description = "\n" +
                            "Attach a rope handle to the high pulley of a cable machine.\n" +
                            "\n" +
                            "Stand upright, facing the machine.\n" +
                            "\n" +
                            "Grasp the rope with a neutral grip (palms facing each other).\n" +
                            "\n" +
                            "Keep your elbows close to your body and your forearms parallel to the floor.\n" +
                            "\n" +
                            "Slightly bend your knees and keep your core tight for stability.\n",
                    weight = "12kg",
                    numbers = "12",
                    sets = "4",
                    week = 3

                ),

                TricepsBackEntity(
                    name = "Seated Overhead Dumbbell ",
                    muscleGroup = "Triceps",
                    imagePath = "seated_overhead_dumbbell",
                    description = "\n" +
                            "Sit on a bench with back support to keep your torso stable.\n" +
                            "\n" +
                            "Hold one dumbbell with both hands, placing your palms against the inner plate or handle (as shown).\n" +
                            "\n" +
                            "Raise the dumbbell over your head until your arms are fully extended — this is the starting position.\n" +
                            "\n" +
                            "Keep your elbows close to your head and upper arms vertical (don’t flare out).\n" +
                            "\n" +
                            "Engage your core to prevent arching your lower back.\n",
                    weight = "14kg",
                    numbers = "10",
                    sets = "4",
                    week = 3

                ),

                TricepsBackEntity(
                    name = "Pull-Up",
                    muscleGroup = "Back",
                    imagePath = "pull_up",
                    description = "\n" +
                            "Grasp a pull-up bar with a pronated (overhand) grip, hands slightly wider than shoulder-width apart.\n" +
                            "\n" +
                            "Hang from the bar with your arms fully extended, shoulders down, and core engaged.\n" +
                            "\n" +
                            "Keep your legs straight or slightly bent (crossed at the ankles if preferred).\n",
                    weight = "",
                    numbers = "10",
                    sets = "4",
                    week = 3

                ),

                TricepsBackEntity(
                    name = "Bent-Over Barbell Row",
                    muscleGroup = "Back",
                    imagePath = "bent_over_barbell_row",
                    description = "\n" +
                            "Stand with your feet shoulder-width apart and grasp a barbell with an overhand (pronated) grip.\n" +
                            "\n" +
                            "Slightly bend your knees and hinge forward at the hips, keeping your back straight and your core tight.\n" +
                            "\n" +
                            "The bar should hang just below your knees, your torso leaning forward at about a 45° angle.\n" +
                            "\n" +
                            "Keep your head in line with your spine — look slightly ahead, not up or down.\n",
                    weight = "14kg",
                    numbers = "10",
                    sets = "4",
                    week = 3

                ),

                TricepsBackEntity(
                    name = "Lat Pulldown",
                    muscleGroup = "Back",
                    imagePath = "lat_pulldown",
                    description = "\n" +
                            "Sit on the lat pulldown machine and adjust the thigh pad so it fits snugly against your thighs to keep your body stable.\n" +
                            "\n" +
                            "Grasp the bar with a wide overhand grip (palms facing away) — your hands should be slightly wider than shoulder-width apart.\n" +
                            "\n" +
                            "Sit upright with a straight back, chest lifted, and shoulders down and back.\n" +
                            "\n" +
                            "Extend your arms fully so that you feel a stretch in your lats before starting.\n",
                    weight = "18kg",
                    numbers = "10",
                    sets = "4",
                    week = 3

                ),

                TricepsBackEntity(
                    name = "One-Arm Dumbbell Row",
                    muscleGroup = "Back",
                    imagePath = "one_arm_dumbbell_row",
                    description = "\n" +
                            "Place one knee and the same-side hand on a flat bench for support.\n" +
                            "\n" +
                            "Keep your back flat, core tight, and your head aligned with your spine.\n" +
                            "\n" +
                            "Hold a dumbbell in the opposite hand, letting it hang straight down toward the floor with your arm fully extended.\n" +
                            "\n" +
                            "Your shoulders should be square to the ground — don’t twist your torso.\n",
                    weight = "16kg",
                    numbers = "10",
                    sets = "4",
                    week = 3

                ),



                TricepsBackEntity(
                    name = "Rope Triceps Pushdown",
                    muscleGroup = "Triceps",
                    imagePath = "rope_triceps_pushdown",
                    description = "\n" +
                            "Attach a rope handle to the high pulley of a cable machine.\n" +
                            "\n" +
                            "Sit or stand (as shown in the image — seated variation helps with stability).\n" +
                            "\n" +
                            "Grasp the rope with a neutral grip (palms facing each other).\n" +
                            "\n" +
                            "Keep your elbows close to your torso and bent at about 90°.\n" +
                            "\n" +
                            "Maintain an upright posture with your chest up and shoulders back.\n",
                    weight = "18kg",
                    numbers = "10",
                    sets = "4",
                    week = 4

                ),
                TricepsBackEntity(
                    name = "Overhead Cable Triceps",
                    muscleGroup = "Triceps",
                    imagePath = "overhead_cable_triceps",
                    description = "\n" +
                            "Sit on a bench with back support in front of a low pulley cable machine.\n" +
                            "\n" +
                            "Attach a rope handle (or single handle) to the low pulley.\n" +
                            "\n" +
                            "Grab the handle with both hands and bring it overhead so your arms are bent and elbows pointing up, as shown in the photo.\n" +
                            "\n" +
                            "Keep your core tight and back straight.\n",
                    weight = "16kg",
                    numbers = "10",
                    sets = "4",
                    week = 4

                ),
                TricepsBackEntity(
                    name = "Cable Rope Triceps Pushdown",
                    muscleGroup = "Triceps",
                    imagePath = "cable_rope_triceps_pushdown",
                    description = "\n" +
                            "Attach a rope handle to the high pulley of a cable machine.\n" +
                            "\n" +
                            "Stand upright, facing the machine.\n" +
                            "\n" +
                            "Grasp the rope with a neutral grip (palms facing each other).\n" +
                            "\n" +
                            "Keep your elbows close to your body and your forearms parallel to the floor.\n" +
                            "\n" +
                            "Slightly bend your knees and keep your core tight for stability.\n",
                    weight = "16kg",
                    numbers = "10",
                    sets = "4",
                    week = 4

                ),

                TricepsBackEntity(
                    name = "Seated Overhead Dumbbell ",
                    muscleGroup = "Triceps",
                    imagePath = "seated_overhead_dumbbell",
                    description = "\n" +
                            "Sit on a bench with back support to keep your torso stable.\n" +
                            "\n" +
                            "Hold one dumbbell with both hands, placing your palms against the inner plate or handle (as shown).\n" +
                            "\n" +
                            "Raise the dumbbell over your head until your arms are fully extended — this is the starting position.\n" +
                            "\n" +
                            "Keep your elbows close to your head and upper arms vertical (don’t flare out).\n" +
                            "\n" +
                            "Engage your core to prevent arching your lower back.\n",
                    weight = "18kg",
                    numbers = "10",
                    sets = "4",
                    week = 4

                ),

                TricepsBackEntity(
                    name = "Pull-Up",
                    muscleGroup = "Back",
                    imagePath = "pull_up",
                    description = "\n" +
                            "Grasp a pull-up bar with a pronated (overhand) grip, hands slightly wider than shoulder-width apart.\n" +
                            "\n" +
                            "Hang from the bar with your arms fully extended, shoulders down, and core engaged.\n" +
                            "\n" +
                            "Keep your legs straight or slightly bent (crossed at the ankles if preferred).\n",
                    weight = "",
                    numbers = "12",
                    sets = "4",
                    week = 4

                ),

                TricepsBackEntity(
                    name = "Bent-Over Barbell Row",
                    muscleGroup = "Back",
                    imagePath = "bent_over_barbell_row",
                    description = "\n" +
                            "Stand with your feet shoulder-width apart and grasp a barbell with an overhand (pronated) grip.\n" +
                            "\n" +
                            "Slightly bend your knees and hinge forward at the hips, keeping your back straight and your core tight.\n" +
                            "\n" +
                            "The bar should hang just below your knees, your torso leaning forward at about a 45° angle.\n" +
                            "\n" +
                            "Keep your head in line with your spine — look slightly ahead, not up or down.\n",
                    weight = "18kg",
                    numbers = "10",
                    sets = "4",
                    week = 4

                ),

                TricepsBackEntity(
                    name = "Lat Pulldown",
                    muscleGroup = "Back",
                    imagePath = "lat_pulldown",
                    description = "\n" +
                            "Sit on the lat pulldown machine and adjust the thigh pad so it fits snugly against your thighs to keep your body stable.\n" +
                            "\n" +
                            "Grasp the bar with a wide overhand grip (palms facing away) — your hands should be slightly wider than shoulder-width apart.\n" +
                            "\n" +
                            "Sit upright with a straight back, chest lifted, and shoulders down and back.\n" +
                            "\n" +
                            "Extend your arms fully so that you feel a stretch in your lats before starting.\n",
                    weight = "20kg",
                    numbers = "10",
                    sets = "4",
                    week = 4

                ),

                TricepsBackEntity(
                    name = "One-Arm Dumbbell Row",
                    muscleGroup = "Back",
                    imagePath = "one_arm_dumbbell_row",
                    description = "\n" +
                            "Place one knee and the same-side hand on a flat bench for support.\n" +
                            "\n" +
                            "Keep your back flat, core tight, and your head aligned with your spine.\n" +
                            "\n" +
                            "Hold a dumbbell in the opposite hand, letting it hang straight down toward the floor with your arm fully extended.\n" +
                            "\n" +
                            "Your shoulders should be square to the ground — don’t twist your torso.\n",
                    weight = "18kg",
                    numbers = "10",
                    sets = "4",
                    week = 4

                )

            )
        }





        private fun getPradeFinedSoulderLegsEntity(context: Context): List<ShoulderLegsEntity> {
            return listOf(
                ShoulderLegsEntity(
                    name = "Dumbbell Lateral Raise",
                    muscleGroup = "Shoulder",
                    imagePath = "dumbbell_lateral_raise",
                    description = "\n" +
                            "Stand (or sit, as shown) with a dumbbell in each hand, arms down by your sides.\n" +
                            "\n" +
                            "Keep a slight bend in your elbows (don’t lock them).\n" +
                            "\n" +
                            "Maintain an upright posture with your core tight and shoulders back.\n" +
                            "\n" +
                            "Your palms should face inward toward your thighs (neutral grip).\n",
                    weight = "5kg",
                    numbers = "10",
                    sets = "3",
                    week = 1


                ),

                ShoulderLegsEntity(
                    name = "Seated Dumbbell Shoulder Press",
                    muscleGroup = "Shoulder",
                    imagePath = "seated_dumbbell_shoulder_press",
                    description = "Sit on a bench with back support to stabilize your torso.\n" +
                            "\n" +
                            "Hold a dumbbell in each hand at shoulder height, palms facing forward.\n" +
                            "\n" +
                            "Keep your feet flat on the floor, your core tight, and your back straight against the bench.\n" +
                            "\n" +
                            "Your elbows should be bent at about 90°, slightly in front of your body (not flared out directly to the sides).\n",
                    weight = "7kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),

                ShoulderLegsEntity(
                    name = "Seated Dumbbell Front Raise",
                    muscleGroup = "Shoulder",
                    imagePath = "seated_dumbbell_front_raise",
                    description = "\n" +
                            "Sit on a bench with back support and hold a dumbbell in each hand in front of your thighs.\n" +
                            "\n" +
                            "Your palms can face down (pronated grip) or inward (neutral grip) — both target the front delts, but pronated focuses more directly on them.\n" +
                            "\n" +
                            "Keep your back straight, core tight, and shoulders down and back.\n",
                    weight = "5kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),

                ShoulderLegsEntity(
                    name = "Machine Shoulder Press",
                    muscleGroup = "Shoulder",
                    imagePath = "machine_shoulder_press",
                    description = "Sit on the machine with your back flat against the pad.\n" +
                            "\n" +
                            "Adjust the seat height so that the handles are roughly at shoulder level.\n" +
                            "\n" +
                            "Grasp the handles with a pronated grip (palms facing forward).\n" +
                            "\n" +
                            "Keep your feet flat on the floor, your core tight, and your chest up.\n" +
                            "\n" +
                            "Your elbows should start at about 90°, slightly in front of your body (not directly to the sides).\n",
                    weight = "12kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),

                ShoulderLegsEntity(
                    name = "Leg Press",
                    muscleGroup = "Legs",
                    imagePath = "leg_press",
                    description = "\n" +
                            "Sit down on the leg press machine and place your feet shoulder-width apart on the platform.\n" +
                            "\n" +
                            "Your toes should point slightly outward (about 10–15°).\n" +
                            "\n" +
                            "Adjust the seat so that when your feet are on the platform, your knees form about a 90° angle.\n" +
                            "\n" +
                            "Keep your back and head pressed against the seat pad throughout the entire movement.\n",
                    weight = "50kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),

                ShoulderLegsEntity(
                    name = "Step-Up",
                    muscleGroup = "Legs",
                    imagePath = "step_up",
                    description = "Stand in front of a sturdy bench, box, or platform (around knee height).\n" +
                            "\n" +
                            "Keep your feet shoulder-width apart and your chest upright.\n" +
                            "\n" +
                            "Engage your core to stabilize your body.\n",
                    weight = "",
                    numbers = "15",
                    sets = "3",
                    week = 1

                ),

                ShoulderLegsEntity(
                    name = "Seated Calf Raise",
                    muscleGroup = "Legs",
                    imagePath = "seated_calf_raise",
                    description = "\n" +
                            "Sit down on a seated calf raise machine or a bench with a barbell/dumbbell across your thighs (as shown in the image).\n" +
                            "\n" +
                            "Place the balls of your feet on the platform or block, with your heels hanging off the edge.\n" +
                            "\n" +
                            "Adjust the pad or bar so it rests comfortably on your lower thighs, just above the knees.\n" +
                            "\n" +
                            "Keep your back straight, core engaged, and feet hip-width apart.\n",
                    weight = "15kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),

                ShoulderLegsEntity(
                    name = "Barbell Back Squat",
                    muscleGroup = "Legs",
                    imagePath = "barbell_back_squat",
                    description = "\n" +
                            "Position a barbell on a squat rack slightly below shoulder height.\n" +
                            "\n" +
                            "Step under the bar and place it across your upper back/trapezius muscles (not on your neck).\n" +
                            "\n" +
                            "Grip the bar slightly wider than shoulder width and squeeze your shoulder blades together to create a stable upper back.\n" +
                            "\n" +
                            "Stand up to lift the bar off the rack and take a small step backward.\n" +
                            "\n" +
                            "Place your feet shoulder-width apart, toes pointed slightly outward (10–15°).\n" +
                            "\n" +
                            "Keep your chest up, core tight, and eyes looking forward.\n",
                    weight = "20kg",
                    numbers = "10",
                    sets = "3",
                    week = 1

                ),




                ShoulderLegsEntity(
                    name = "Dumbbell Lateral Raise",
                    muscleGroup = "Shoulder",
                    imagePath = "dumbbell_lateral_raise",
                    description = "\n" +
                            "Stand (or sit, as shown) with a dumbbell in each hand, arms down by your sides.\n" +
                            "\n" +
                            "Keep a slight bend in your elbows (don’t lock them).\n" +
                            "\n" +
                            "Maintain an upright posture with your core tight and shoulders back.\n" +
                            "\n" +
                            "Your palms should face inward toward your thighs (neutral grip).\n",
                    weight = "7kg",
                    numbers = "12",
                    sets = "4",
                    week = 2


                ),

                ShoulderLegsEntity(
                    name = "Seated Dumbbell Shoulder Press",
                    muscleGroup = "Shoulder",
                    imagePath = "seated_dumbbell_shoulder_press",
                    description = "Sit on a bench with back support to stabilize your torso.\n" +
                            "\n" +
                            "Hold a dumbbell in each hand at shoulder height, palms facing forward.\n" +
                            "\n" +
                            "Keep your feet flat on the floor, your core tight, and your back straight against the bench.\n" +
                            "\n" +
                            "Your elbows should be bent at about 90°, slightly in front of your body (not flared out directly to the sides).\n",
                    weight = "10kg",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),

                ShoulderLegsEntity(
                    name = "Seated Dumbbell Front Raise",
                    muscleGroup = "Shoulder",
                    imagePath = "seated_dumbbell_front_raise",
                    description = "\n" +
                            "Sit on a bench with back support and hold a dumbbell in each hand in front of your thighs.\n" +
                            "\n" +
                            "Your palms can face down (pronated grip) or inward (neutral grip) — both target the front delts, but pronated focuses more directly on them.\n" +
                            "\n" +
                            "Keep your back straight, core tight, and shoulders down and back.\n",
                    weight = "7kg",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),

                ShoulderLegsEntity(
                    name = "Machine Shoulder Press",
                    muscleGroup = "Shoulder",
                    imagePath = "machine_shoulder_press",
                    description = "Sit on the machine with your back flat against the pad.\n" +
                            "\n" +
                            "Adjust the seat height so that the handles are roughly at shoulder level.\n" +
                            "\n" +
                            "Grasp the handles with a pronated grip (palms facing forward).\n" +
                            "\n" +
                            "Keep your feet flat on the floor, your core tight, and your chest up.\n" +
                            "\n" +
                            "Your elbows should start at about 90°, slightly in front of your body (not directly to the sides).\n",
                    weight = "14kg",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),

                ShoulderLegsEntity(
                    name = "Leg Press",
                    muscleGroup = "Legs",
                    imagePath = "leg_press",
                    description = "\n" +
                            "Sit down on the leg press machine and place your feet shoulder-width apart on the platform.\n" +
                            "\n" +
                            "Your toes should point slightly outward (about 10–15°).\n" +
                            "\n" +
                            "Adjust the seat so that when your feet are on the platform, your knees form about a 90° angle.\n" +
                            "\n" +
                            "Keep your back and head pressed against the seat pad throughout the entire movement.\n",
                    weight = "54kg",
                    numbers = "10",
                    sets = "3",
                    week = 2

                ),

                ShoulderLegsEntity(
                    name = "Step-Up",
                    muscleGroup = "Legs",
                    imagePath = "step_up",
                    description = "Stand in front of a sturdy bench, box, or platform (around knee height).\n" +
                            "\n" +
                            "Keep your feet shoulder-width apart and your chest upright.\n" +
                            "\n" +
                            "Engage your core to stabilize your body.\n",
                    weight = "",
                    numbers = "18",
                    sets = "4",
                    week = 2

                ),

                ShoulderLegsEntity(
                    name = "Seated Calf Raise",
                    muscleGroup = "Legs",
                    imagePath = "seated_calf_raise",
                    description = "\n" +
                            "Sit down on a seated calf raise machine or a bench with a barbell/dumbbell across your thighs (as shown in the image).\n" +
                            "\n" +
                            "Place the balls of your feet on the platform or block, with your heels hanging off the edge.\n" +
                            "\n" +
                            "Adjust the pad or bar so it rests comfortably on your lower thighs, just above the knees.\n" +
                            "\n" +
                            "Keep your back straight, core engaged, and feet hip-width apart.\n",
                    weight = "18kg",
                    numbers = "12",
                    sets = "4",
                    week = 2

                ),

                ShoulderLegsEntity(
                    name = "Barbell Back Squat",
                    muscleGroup = "Legs",
                    imagePath = "barbell_back_squat",
                    description = "\n" +
                            "Position a barbell on a squat rack slightly below shoulder height.\n" +
                            "\n" +
                            "Step under the bar and place it across your upper back/trapezius muscles (not on your neck).\n" +
                            "\n" +
                            "Grip the bar slightly wider than shoulder width and squeeze your shoulder blades together to create a stable upper back.\n" +
                            "\n" +
                            "Stand up to lift the bar off the rack and take a small step backward.\n" +
                            "\n" +
                            "Place your feet shoulder-width apart, toes pointed slightly outward (10–15°).\n" +
                            "\n" +
                            "Keep your chest up, core tight, and eyes looking forward.\n",
                    weight = "25kg",
                    numbers = "10",
                    sets = "4",
                    week = 2

                ),



                ShoulderLegsEntity(
                    name = "Dumbbell Lateral Raise",
                    muscleGroup = "Shoulder",
                    imagePath = "dumbbell_lateral_raise",
                    description = "\n" +
                            "Stand (or sit, as shown) with a dumbbell in each hand, arms down by your sides.\n" +
                            "\n" +
                            "Keep a slight bend in your elbows (don’t lock them).\n" +
                            "\n" +
                            "Maintain an upright posture with your core tight and shoulders back.\n" +
                            "\n" +
                            "Your palms should face inward toward your thighs (neutral grip).\n",
                    weight = "10kg",
                    numbers = "12",
                    sets = "4",
                    week = 3


                ),

                ShoulderLegsEntity(
                    name = "Seated Dumbbell Shoulder Press",
                    muscleGroup = "Shoulder",
                    imagePath = "seated_dumbbell_shoulder_press",
                    description = "Sit on a bench with back support to stabilize your torso.\n" +
                            "\n" +
                            "Hold a dumbbell in each hand at shoulder height, palms facing forward.\n" +
                            "\n" +
                            "Keep your feet flat on the floor, your core tight, and your back straight against the bench.\n" +
                            "\n" +
                            "Your elbows should be bent at about 90°, slightly in front of your body (not flared out directly to the sides).\n",
                    weight = "12kg",
                    numbers = "12",
                    sets = "4",
                    week = 3

                ),

                ShoulderLegsEntity(
                    name = "Seated Dumbbell Front Raise",
                    muscleGroup = "Shoulder",
                    imagePath = "seated_dumbbell_front_raise",
                    description = "\n" +
                            "Sit on a bench with back support and hold a dumbbell in each hand in front of your thighs.\n" +
                            "\n" +
                            "Your palms can face down (pronated grip) or inward (neutral grip) — both target the front delts, but pronated focuses more directly on them.\n" +
                            "\n" +
                            "Keep your back straight, core tight, and shoulders down and back.\n",
                    weight = "10kg",
                    numbers = "12",
                    sets = "4",
                    week = 3

                ),

                ShoulderLegsEntity(
                    name = "Machine Shoulder Press",
                    muscleGroup = "Shoulder",
                    imagePath = "machine_shoulder_press",
                    description = "Sit on the machine with your back flat against the pad.\n" +
                            "\n" +
                            "Adjust the seat height so that the handles are roughly at shoulder level.\n" +
                            "\n" +
                            "Grasp the handles with a pronated grip (palms facing forward).\n" +
                            "\n" +
                            "Keep your feet flat on the floor, your core tight, and your chest up.\n" +
                            "\n" +
                            "Your elbows should start at about 90°, slightly in front of your body (not directly to the sides).\n",
                    weight = "16kg",
                    numbers = "12",
                    sets = "4",
                    week = 3

                ),

                ShoulderLegsEntity(
                    name = "Leg Press",
                    muscleGroup = "Legs",
                    imagePath = "leg_press",
                    description = "\n" +
                            "Sit down on the leg press machine and place your feet shoulder-width apart on the platform.\n" +
                            "\n" +
                            "Your toes should point slightly outward (about 10–15°).\n" +
                            "\n" +
                            "Adjust the seat so that when your feet are on the platform, your knees form about a 90° angle.\n" +
                            "\n" +
                            "Keep your back and head pressed against the seat pad throughout the entire movement.\n",
                    weight = "60kg",
                    numbers = "12",
                    sets = "4",
                    week = 3

                ),

                ShoulderLegsEntity(
                    name = "Step-Up",
                    muscleGroup = "Legs",
                    imagePath = "step_up",
                    description = "Stand in front of a sturdy bench, box, or platform (around knee height).\n" +
                            "\n" +
                            "Keep your feet shoulder-width apart and your chest upright.\n" +
                            "\n" +
                            "Engage your core to stabilize your body.\n",
                    weight = "",
                    numbers = "20",
                    sets = "5",
                    week = 3

                ),

                ShoulderLegsEntity(
                    name = "Seated Calf Raise",
                    muscleGroup = "Legs",
                    imagePath = "seated_calf_raise",
                    description = "\n" +
                            "Sit down on a seated calf raise machine or a bench with a barbell/dumbbell across your thighs (as shown in the image).\n" +
                            "\n" +
                            "Place the balls of your feet on the platform or block, with your heels hanging off the edge.\n" +
                            "\n" +
                            "Adjust the pad or bar so it rests comfortably on your lower thighs, just above the knees.\n" +
                            "\n" +
                            "Keep your back straight, core engaged, and feet hip-width apart.\n",
                    weight = "20kg",
                    numbers = "15",
                    sets = "4",
                    week = 3

                ),

                ShoulderLegsEntity(
                    name = "Barbell Back Squat",
                    muscleGroup = "Legs",
                    imagePath = "barbell_back_squat",
                    description = "\n" +
                            "Position a barbell on a squat rack slightly below shoulder height.\n" +
                            "\n" +
                            "Step under the bar and place it across your upper back/trapezius muscles (not on your neck).\n" +
                            "\n" +
                            "Grip the bar slightly wider than shoulder width and squeeze your shoulder blades together to create a stable upper back.\n" +
                            "\n" +
                            "Stand up to lift the bar off the rack and take a small step backward.\n" +
                            "\n" +
                            "Place your feet shoulder-width apart, toes pointed slightly outward (10–15°).\n" +
                            "\n" +
                            "Keep your chest up, core tight, and eyes looking forward.\n",
                    weight = "25kg",
                    numbers = "12",
                    sets = "4",
                    week = 3

                ),



                ShoulderLegsEntity(
                    name = "Dumbbell Lateral Raise",
                    muscleGroup = "Shoulder",
                    imagePath = "dumbbell_lateral_raise",
                    description = "\n" +
                            "Stand (or sit, as shown) with a dumbbell in each hand, arms down by your sides.\n" +
                            "\n" +
                            "Keep a slight bend in your elbows (don’t lock them).\n" +
                            "\n" +
                            "Maintain an upright posture with your core tight and shoulders back.\n" +
                            "\n" +
                            "Your palms should face inward toward your thighs (neutral grip).\n",
                    weight = "14kg",
                    numbers = "12",
                    sets = "4",
                    week = 4


                ),

                ShoulderLegsEntity(
                    name = "Seated Dumbbell Shoulder Press",
                    muscleGroup = "Shoulder",
                    imagePath = "seated_dumbbell_shoulder_press",
                    description = "Sit on a bench with back support to stabilize your torso.\n" +
                            "\n" +
                            "Hold a dumbbell in each hand at shoulder height, palms facing forward.\n" +
                            "\n" +
                            "Keep your feet flat on the floor, your core tight, and your back straight against the bench.\n" +
                            "\n" +
                            "Your elbows should be bent at about 90°, slightly in front of your body (not flared out directly to the sides).\n",
                    weight = "14kg",
                    numbers = "12",
                    sets = "4",
                    week = 4

                ),

                ShoulderLegsEntity(
                    name = "Seated Dumbbell Front Raise",
                    muscleGroup = "Shoulder",
                    imagePath = "seated_dumbbell_front_raise",
                    description = "\n" +
                            "Sit on a bench with back support and hold a dumbbell in each hand in front of your thighs.\n" +
                            "\n" +
                            "Your palms can face down (pronated grip) or inward (neutral grip) — both target the front delts, but pronated focuses more directly on them.\n" +
                            "\n" +
                            "Keep your back straight, core tight, and shoulders down and back.\n",
                    weight = "12kg",
                    numbers = "12",
                    sets = "4",
                    week = 4

                ),

                ShoulderLegsEntity(
                    name = "Machine Shoulder Press",
                    muscleGroup = "Shoulder",
                    imagePath = "machine_shoulder_press",
                    description = "Sit on the machine with your back flat against the pad.\n" +
                            "\n" +
                            "Adjust the seat height so that the handles are roughly at shoulder level.\n" +
                            "\n" +
                            "Grasp the handles with a pronated grip (palms facing forward).\n" +
                            "\n" +
                            "Keep your feet flat on the floor, your core tight, and your chest up.\n" +
                            "\n" +
                            "Your elbows should start at about 90°, slightly in front of your body (not directly to the sides).\n",
                    weight = "16kg",
                    numbers = "12",
                    sets = "4",
                    week = 4

                ),

                ShoulderLegsEntity(
                    name = "Leg Press",
                    muscleGroup = "Legs",
                    imagePath = "leg_press",
                    description = "\n" +
                            "Sit down on the leg press machine and place your feet shoulder-width apart on the platform.\n" +
                            "\n" +
                            "Your toes should point slightly outward (about 10–15°).\n" +
                            "\n" +
                            "Adjust the seat so that when your feet are on the platform, your knees form about a 90° angle.\n" +
                            "\n" +
                            "Keep your back and head pressed against the seat pad throughout the entire movement.\n",
                    weight = "65kg",
                    numbers = "10",
                    sets = "4",
                    week = 4

                ),

                ShoulderLegsEntity(
                    name = "Step-Up",
                    muscleGroup = "Legs",
                    imagePath = "step_up",
                    description = "Stand in front of a sturdy bench, box, or platform (around knee height).\n" +
                            "\n" +
                            "Keep your feet shoulder-width apart and your chest upright.\n" +
                            "\n" +
                            "Engage your core to stabilize your body.\n",
                    weight = "",
                    numbers = "25",
                    sets = "5",
                    week = 4

                ),

                ShoulderLegsEntity(
                    name = "Seated Calf Raise",
                    muscleGroup = "Legs",
                    imagePath = "seated_calf_raise",
                    description = "\n" +
                            "Sit down on a seated calf raise machine or a bench with a barbell/dumbbell across your thighs (as shown in the image).\n" +
                            "\n" +
                            "Place the balls of your feet on the platform or block, with your heels hanging off the edge.\n" +
                            "\n" +
                            "Adjust the pad or bar so it rests comfortably on your lower thighs, just above the knees.\n" +
                            "\n" +
                            "Keep your back straight, core engaged, and feet hip-width apart.\n",
                    weight = "25kg",
                    numbers = "12",
                    sets = "4",
                    week = 4

                ),

                ShoulderLegsEntity(
                    name = "Barbell Back Squat",
                    muscleGroup = "Legs",
                    imagePath = "barbell_back_squat",
                    description = "\n" +
                            "Position a barbell on a squat rack slightly below shoulder height.\n" +
                            "\n" +
                            "Step under the bar and place it across your upper back/trapezius muscles (not on your neck).\n" +
                            "\n" +
                            "Grip the bar slightly wider than shoulder width and squeeze your shoulder blades together to create a stable upper back.\n" +
                            "\n" +
                            "Stand up to lift the bar off the rack and take a small step backward.\n" +
                            "\n" +
                            "Place your feet shoulder-width apart, toes pointed slightly outward (10–15°).\n" +
                            "\n" +
                            "Keep your chest up, core tight, and eyes looking forward.\n",
                    weight = "30kg",
                    numbers = "10",
                    sets = "4",
                    week = 4

                ),


                )
        }

    }
}