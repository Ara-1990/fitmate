package com.the.fitmate.domain

import com.the.fitmate.data.InsertExersize
import javax.inject.Inject

class InsertExerUseCase @Inject constructor(private val exerRepo: SaveGetExerRepos) {

    suspend operator fun invoke(item: InsertExersize) {
         exerRepo.insertExersize(item)
    }
}