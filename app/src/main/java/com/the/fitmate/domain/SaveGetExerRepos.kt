package com.the.fitmate.domain

import com.the.fitmate.data.InsertExersize
import kotlinx.coroutines.flow.Flow

interface SaveGetExerRepos {
    suspend fun insertExersize(exercise: InsertExersize)
    fun getAllSavedExercises(): Flow<List<InsertExersize>>

}