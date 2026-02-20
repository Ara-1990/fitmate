package com.the.fitmate.domain

class GetAllExerUseCase(private val exerRepo: ExerciseRepository) {

    suspend operator fun invoke(): List<Exercise> {
        return exerRepo.getAll()
    }
}