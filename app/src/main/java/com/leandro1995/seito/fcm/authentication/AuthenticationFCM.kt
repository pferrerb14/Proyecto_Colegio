package com.leandro1995.seito.fcm.authentication

import com.google.firebase.Firebase
import com.google.firebase.auth.auth

class AuthenticationFCM {

    private val auth = Firebase.auth

    fun signInWithEmailAndPassword(
        email: String, password: String, success: (email: String) -> Unit, error: () -> Unit
    ) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                success(auth.currentUser?.email.orEmpty())
            } else {
                error()
            }
        }
    }

    fun createUserWithEmailAndPassword(
        email: String, password: String, success: () -> Unit, error: () -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                success()
            } else {
                error()
            }
        }
    }
}