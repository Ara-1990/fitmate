package com.the.fitmate.data


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercises ORDER BY name ASC")
    suspend fun getAll(): List<ExerciseEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(exercise: List<ExerciseEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBicBrest(exercise: List<BicepsBrestEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTricepsBack(exercise: List<TricepsBackEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSholderLegs(exercise: List<ShoulderLegsEntity>)




    @Query("SELECT * FROM biceps_brest WHERE week = :week ORDER BY name ASC")
    suspend fun getBicBrestByWeek(week: Int): List<BicepsBrestEntity>


    @Query("SELECT * FROM triceps_back WHERE week = :week ORDER BY name ASC")
    suspend fun getTricepsBackByWeek(week: Int): List<TricepsBackEntity>


    @Query("SELECT * FROM shoulder_legs WHERE week = :week ORDER BY name ASC")
    suspend fun getShoulderLegsByWeek(week: Int): List<ShoulderLegsEntity>


    @Query("SELECT COUNT(*) FROM biceps_brest")
    suspend fun getAnyBicBrestCount(): Int

    @Query("SELECT COUNT(*) FROM triceps_back")
    suspend fun getAnyTricepsBackCount(): Int

    @Query("SELECT COUNT(*) FROM shoulder_legs")
    suspend fun getAnyShoulderLegsCount(): Int





}