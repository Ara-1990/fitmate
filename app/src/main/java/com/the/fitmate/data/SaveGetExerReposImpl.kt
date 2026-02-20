package com.the.fitmate.data

import android.util.Log
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.the.fitmate.data.InsertExersize
import com.the.fitmate.domain.SaveGetExerRepos
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.concurrent.CancellationException
import javax.inject.Inject
import kotlin.coroutines.resumeWithException

class SaveGetExerReposImpl @Inject constructor(
    private val database: FirebaseDatabase,
) : SaveGetExerRepos {



    private fun userId(): String? {
        return FirebaseAuth.getInstance().currentUser?.uid

    }

    private fun ref(): DatabaseReference? {
        val uid = userId() ?: return null
        return database.getReference("Users")
            .child(uid)
            .child("savedExercises")
    }





    override suspend fun insertExersize(exercise: InsertExersize) {
        val r = ref()?: return
        val key = r.push().key ?: return
        val item = exercise.copy(id = key)

        r.child(key).setValue(item)


    }

    override fun getAllSavedExercises(): Flow<List<InsertExersize>> = callbackFlow {
        val r = ref()

        if (r == null) {
            trySend(emptyList())
            close()
            return@callbackFlow
        }
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val list = snapshot.children.mapNotNull {
                    it.getValue(InsertExersize::class.java)
                }
                trySend(list)
            }
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        r.addValueEventListener(listener)

        awaitClose { r.removeEventListener(listener) }
    }
    }


