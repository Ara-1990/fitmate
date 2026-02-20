package com.the.fitmate.domain


import com.the.fitmate.data.UsersData

interface UserRepository {

    suspend fun register(email: String, password: String)
    suspend fun login(email: String, password: String): UsersData?
}