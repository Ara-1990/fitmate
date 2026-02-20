package com.the.fitmate.data


import com.the.fitmate.domain.ExerciseRepository


import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ExerciseRepositoryImpl(
    private val dao: ExerciseDao
): ExerciseRepository {
    override suspend fun getAll() = dao.getAll().map { it.toDomain() }


    override suspend fun getBicBrestByweek(week: Int) = dao.getBicBrestByWeek(week).map { it.toDomain() }
    override suspend fun getTricBackByweek(week: Int) = dao.getTricepsBackByWeek(week).map { it.toDomain() }
    override suspend fun getSholderLegsByweek(week: Int) = dao.getShoulderLegsByWeek(week).map { it.toDomain() }

}