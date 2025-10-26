package com.leandro1995.seito.model.entity

import com.leandro1995.seito.fcm.firestore.UserFirestoreFCM
import com.leandro1995.seito.model.entity.ambient.User

class Teacher(
    name: String = "",
    lastName: String = "",
    email: String = "",
    age: Int = -1,
    sex: String = "",
    password: String = "",
    var code: String = ""
) : User(
    name = name, lastName = lastName, email = email, age = age, sex = sex, password = password
) {

    fun codeVerifyFirebase(success: () -> Unit, error: () -> Unit) {
        UserFirestoreFCM().whereEqualToCode(code = code, success = success, error = error)
    }

    fun isCode() = code.isEmpty()

    fun isCodeLength(length: Int) = code.length == length
}