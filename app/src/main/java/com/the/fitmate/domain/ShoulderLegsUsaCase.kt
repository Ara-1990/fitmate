package com.the.fitmate.domain

class ShoulderLegsUsaCase(private val shoulderLegsRepo: ExerciseRepository) {
    suspend operator fun invoke(week:Int): List<Exercise> {
        return shoulderLegsRepo.getSholderLegsByweek(week)
    }

}