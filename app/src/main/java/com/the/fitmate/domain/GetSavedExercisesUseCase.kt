package com.the.fitmate.domain

import com.the.fitmate.data.InsertExersize
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSavedExercisesUseCase @Inject constructor(
    private val repo: SaveGetExerRepos
){
     operator fun invoke(): Flow<List<InsertExersize>> {
        return repo.getAllSavedExercises()
    }
}