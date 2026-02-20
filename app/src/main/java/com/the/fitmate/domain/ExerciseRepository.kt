package com.the.fitmate.domain

import com.the.fitmate.data.InsertExersize

import kotlinx.coroutines.flow.Flow

interface ExerciseRepository {
    suspend fun getAll(): List<Exercise>


    suspend fun getBicBrestByweek(week: Int): List<Exercise>
    suspend fun getTricBackByweek(week: Int): List<Exercise>
    suspend fun getSholderLegsByweek(week: Int): List<Exercise>

}