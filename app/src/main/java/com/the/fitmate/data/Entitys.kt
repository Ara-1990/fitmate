package com.the.fitmate.data

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "exercises",indices = [Index(value = ["name","muscleGroup"], unique = true)])
data class ExerciseEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val muscleGroup: String,
    val imagePath:String? = null

)

@Entity(tableName = "biceps_brest")
data class BicepsBrestEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val muscleGroup: String,
    val imagePath:String? = null,
    val weight:String? = null,
    val numbers: String? = null,
    val sets: String? = null,
    val description: String? = null,
    val week: Int,
    )


@Entity(tableName = "triceps_back")
data class TricepsBackEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val muscleGroup: String,
    val imagePath:String? = null,
    val weight:String? = null,
    val numbers: String? = null,
    val sets: String? = null,
    val description: String? = null,
    val week: Int,
    )


@Entity(tableName = "shoulder_legs")
data class ShoulderLegsEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val muscleGroup: String,
    val imagePath:String? = null,
    val weight:String? = null,
    val numbers:String? = null,
    val sets: String? = null,
    val description: String? = null,
    val week: Int,
    )

