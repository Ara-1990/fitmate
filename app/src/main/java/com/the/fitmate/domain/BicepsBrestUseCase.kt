package com.the.fitmate.domain

class BicepsBrestUseCase(private val biceBrestRepo: ExerciseRepository) {

    suspend operator fun invoke(week: Int): List<Exercise> {
        return biceBrestRepo.getBicBrestByweek(week)
    }

}