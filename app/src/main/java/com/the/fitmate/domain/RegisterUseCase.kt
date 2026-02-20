package com.the.fitmate.domain


import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val regis: UserRepository) {

    suspend operator fun invoke(email:String, password:String):Boolean {
        return try {

            regis.register(email, password)
            true
        } catch (e: Exception) {

            false
        }
    }
}