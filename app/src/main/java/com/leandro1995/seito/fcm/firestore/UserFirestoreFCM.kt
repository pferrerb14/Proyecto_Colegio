package com.leandro1995.seito.fcm.firestore

import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.model.entity.Teacher

class UserFirestoreFCM : FirestoreAmbientFCM() {

    fun whereEqualToStudent(email: String, success: (student: Student) -> Unit, error: () -> Unit) {
        whereEqualTo(
            document = USERS, field = EMAIL, value = email, success = { documentSnapshot ->
                documentSnapshot.first().let { result ->
                    success(
                        Student(
                            name = toString(documentSnapshot = result, field = NAME),
                            lastName = toString(documentSnapshot = result, field = LAST_NAME),
                            email = toString(documentSnapshot = result, field = EMAIL),
                            age = toInt(documentSnapshot = result, field = AGE),
                            sex = toString(documentSnapshot = result, field = SEX),
                            code = toString(documentSnapshot = result, field = CODE),
                            teacher = Teacher(
                                name = toString(
                                    documentSnapshot = result, field = TEACHER
                                )
                            ),
                            coins = toInt(documentSnapshot = result, field = COINS)
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