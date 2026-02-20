package com.the.fitmate.domain


data class Exercise(
    val id: Long? = null,
    val name: String? = null,
    val muscleGroup: String? = null,
    val description: String? = null,
    val weight: String? = null,
    val numbers: String? = null,
    val sets: String? = null,
    val imagePath: String? = null,
    val week: Int = 1,
    )



data class ExercisesUiState(
    val items: List<Exercise> = emptyList(),

)

