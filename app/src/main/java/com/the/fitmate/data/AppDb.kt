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

                )


        }



        private fun getPredefinedTricepsBackEntity(context: Context): List<TricepsBackEntity> {
            return listOf(

            )
        }





        private fun getPradeFinedSoulderLegsEntity(context: Context): List<ShoulderLegsEntity> {
            return listOf(

                )
        }

    }
}
