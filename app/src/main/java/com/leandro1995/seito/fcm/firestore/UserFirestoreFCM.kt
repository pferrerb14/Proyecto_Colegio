package com.leandro1995.seito.fcm.firestore

import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.fcm.firestore.config.Setting
import com.leandro1995.seito.model.entity.Teacher

class UserFirestoreFCM : FirestoreAmbientFCM() {

    fun whereEqualToEmail(
        email: String,
        success: (name: String, lastName: String, age: Int, sex: String, code: String, teacherName: String, coins: Int, email: String) -> Unit,
        error: () -> Unit
    ) {
        whereEqualTo(
            document = Setting.USERS,
            field = Setting.EMAIL,
            value = email,
            success = { documentSnapshot ->
                documentSnapshot.first().let { result ->
                    success(
                        toString(documentSnapshot = result, field = Setting.NAME),
                        toString(documentSnapshot = result, field = Setting.LAST_NAME),
                        toInt(documentSnapshot = result, field = Setting.AGE),
                        toString(documentSnapshot = result, field = Setting.SEX),
                        toString(documentSnapshot = result, field = Setting.CODE),
                        toString(documentSnapshot = result, field = Setting.TEACHER),
                        toInt(documentSnapshot = result, field = Setting.COINS),
                        toString(documentSnapshot = result, field = Setting.EMAIL)
                    )
                }
            },
            error = error
        )
    }

    fun whereEqualToCode(code: String, success: (teacher: Teacher) -> Unit, error: () -> Unit) {
        whereEqualTo(
            document = Setting.USERS,
            field = Setting.CODE,
            value = code,
            success = { documentSnapshot ->
                documentSnapshot.first().let {
                    success(
                        Teacher(
                            name = toString(documentSnapshot = it, field = Setting.NAME),
                            lastName = toString(documentSnapshot = it, field = Setting.LAST_NAME),
                            email = toString(documentSnapshot = it, field = Setting.EMAIL),
                            age = toInt(documentSnapshot = it, field = Setting.AGE),
                            sex = toString(documentSnapshot = it, field = Setting.SEX),
                            code = toString(documentSnapshot = it, field = Setting.CODE)
                        )
                    )
                }
            },
            error = error
        )
    }
}