package com.the.fitmate.domain


import com.the.fitmate.data.UsersData
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val login: UserRepository) {

    suspend operator fun invoke(email:String, password:String): UsersData? {
       return try {
           login.login(email, password)
       }catch (e: Exception){
           null
       }
    }
}