package com.the.fitmate.data

import androidx.annotation.Keep

@Keep
data class UsersData (
    val email: String,
    val displayName: String? = null
)