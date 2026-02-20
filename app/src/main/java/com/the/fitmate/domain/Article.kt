package com.the.fitmate.domain

data class Article(

    val id: Int,
    val title: String,
    val summary: String,
    val content: String,
    val imagePath: String? = null
)

object SampleData {
    val articles = listOf(
        Article(
            id = 1,
            title = "How to warm up properly",
            summary = "A short warm-up before exercise reduces the risk of injury.....",
            content = "The warm-up should take 5–10 minutes. Start with a light cardio warm-up, followed by dynamic stretching. Examples of exercises include leg swings, shoulder rolls, and light jogging in place"
        ),
        Article(
            id = 2,
            title = "Proper nutrition for muscle growth",
            summary = "Diet Basics: Protein, Carbohydrates, and Healthy Calories.....",
            content = "To build muscle, you need a calorie surplus and adequate protein intake (~1.6–2.2 g/kg). Eat complex carbohydrates, vegetables, and don't forget to rest"
        ),
        Article(
            id = 3,
            title = "General recommendations for performing exercises",
            summary = "Proper technique and gradual increases in load are the key to progress and injury prevention.....",
            content = "1) Technique check. Always start with no weight or light weight to practice proper form  \n" +
                    "2) Body position. Keep your core stable, gaze forward, and shoulders down. Don't hold your breath—exhale during the effort phase  \n" +
                    "3) Range of motion. Perform the exercise through a full, yet safe, range of motion—don't push the depth if you feel any discomfort in your joints  \n" +
                    "4) Progression. Increase the weight or number of reps gradually (e.g., +5–10% every 1–2 weeks) to avoid overloading the ligaments and tendons  \n" +
                    "5) Recovery. Rest between sets and adequate sleep are critical for strength and muscle growth  \n" +
                    "6) Warm-up and cool-down. Include a dynamic warm-up before your workout and a light cool-down/stretching afterward—this reduces the risk of injury and promotes recovery  \n" +
                    "7) Individuality. Choose exercises based on your fitness level, age, and any limitations. If you have chronic pain, consult a doctor or trainer"
        ),

    )
}
