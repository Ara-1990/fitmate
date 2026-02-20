package com.the.fitmate.data

import com.google.android.gms.tasks.Task
import com.google.firebase.database.FirebaseDatabase

import com.the.fitmate.domain.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.suspendCancellableCoroutine
import java.security.MessageDigest
import java.util.concurrent.CancellationException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class UserRepositoryImpl(private val database: FirebaseDatabase) : UserRepository {


    private val auth = FirebaseAuth.getInstance()
    private val usersRef = database.getReference("Users")

    override suspend fun register(email: String, password: String) {
        val result = auth.createUserWithEmailAndPassword(email, password).awaitTask()
        val userId = result.user?.uid ?: throw Exception("User ID is null")

        val payload = mapOf(
            "email" to email
        )

        usersRef.child(userId).setValue(payload).awaitTask()

    }

    override suspend fun login(email: String, password: String): UsersData? {
        val result = auth.signInWithEmailAndPassword(email, password).awaitTask()
        val userId = result.user?.uid ?: return null

        val snapshot = usersRef.child(userId).get().awaitTask()
        if (!snapshot.exists()) return UsersData(email = email)

        val emailDb = snapshot.child("email").getValue(String::class.java) ?: email

        return UsersData(email = emailDb)

    }


    private suspend fun <T> Task<T>.awaitTask(): T = suspendCancellableCoroutine { cont ->

        addOnSuccessListener { result ->
            if (cont.isActive) cont.resume(result) {}
        }

        addOnFailureListener { e ->
            if (cont.isActive) cont.resumeWithException(e)
        }

        addOnCanceledListener {
            if (cont.isActive)
                cont.resumeWithException(CancellationException("Task was cancelled"))
        }
    }


}