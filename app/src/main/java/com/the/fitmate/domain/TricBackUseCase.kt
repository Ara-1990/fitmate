package com.the.fitmate.domain

class TricBackUseCase(private val tricBackRepo: ExerciseRepository) {
    suspend operator fun invoke(week: Int): List<Exercise> {
        return tricBackRepo.getTricBackByweek(week)
    }

}