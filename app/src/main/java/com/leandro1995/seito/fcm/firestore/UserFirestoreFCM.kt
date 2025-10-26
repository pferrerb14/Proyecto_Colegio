package com.leandro1995.seito.fcm.firestore

import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.model.entity.Teacher

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

    fun whereEqualToCode(code: String, success: (teacher: Teacher) -> Unit, error: () -> Unit) {
        whereEqualTo(
            document = USERS, field = CODE, value = code, success = { documentSnapshot ->
                documentSnapshot.first().let {
                    success(
                        Teacher(
                            name = toString(documentSnapshot = it, field = NAME),
                            lastName = toString(documentSnapshot = it, field = LAST_NAME),
                            email = toString(documentSnapshot = it, field = EMAIL),
                            age = toInt(documentSnapshot = it, field = AGE),
                            sex = toString(documentSnapshot = it, field = SEX),
                            code = toString(documentSnapshot = it, field = CODE)
                        )
                    )
                }
            }, error = error
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