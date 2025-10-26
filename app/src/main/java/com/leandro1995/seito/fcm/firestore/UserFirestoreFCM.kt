package com.leandro1995.seito.fcm.firestore

import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM

class UserFirestoreFCM : FirestoreAmbientFCM() {

    fun whereEqualToEmail(
        email: String,
        success: (name: String, lastName: String, age: Int, sex: String, code: String, teacherName: String, coins: Int) -> Unit,
        error: () -> Unit
    ) {
        whereEqualTo(
            document = USERS, field = EMAIL, value = email, success = { documentSnapshot ->
                documentSnapshot.first().let { result ->
                    success(
                        toString(documentSnapshot = result, field = NAME),
                        toString(documentSnapshot = result, field = LAST_NAME),
                        toInt(documentSnapshot = result, field = AGE),
                        toString(documentSnapshot = result, field = SEX),
                        toString(documentSnapshot = result, field = CODE),
                        toString(documentSnapshot = result, field = TEACHER),
                        toInt(documentSnapshot = result, field = COINS)
                    )
                }
            }, error = error
        )
    }

    fun whereEqualToCode(code: String, success: () -> Unit, error: () -> Unit) {
        whereEqualTo(
            document = USERS,
            field = CODE,
            value = code,
            success = { _ -> success() },
            error = error
        )
    }

    companion object {
        private const val USERS = "users"
        private const val EMAIL = "email"
        private const val NAME = "name"
        private const val LAST_NAME = "last_name"
        private const val AGE = "age"
        private const val SEX = "sex"
        private const val CODE = "code"
        private const val TEACHER = "teacher"
        private const val COINS = "coins"
    }
}