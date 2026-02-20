package com.the.fitmate.data

import com.the.fitmate.domain.Exercise


fun ExerciseEntity.toDomain() =
    Exercise(id = id, name = name, muscleGroup = muscleGroup,   imagePath =  imagePath)
fun BicepsBrestEntity.toDomain() =
    Exercise(id =id, name =name, muscleGroup =muscleGroup,  imagePath =  imagePath, description = description, weight = weight, numbers = numbers, sets = sets,

    )
fun TricepsBackEntity.toDomain() =
    Exercise(id =id, name =name, muscleGroup =muscleGroup,  imagePath =  imagePath, description = description, weight = weight, numbers = numbers, sets = sets,

    )
fun ShoulderLegsEntity.toDomain() =
    Exercise(id =id, name =name, muscleGroup =muscleGroup,  imagePath =  imagePath, description = description, weight = weight, numbers = numbers, sets = sets,

    )



