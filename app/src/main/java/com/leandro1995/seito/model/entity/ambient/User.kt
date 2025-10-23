package com.leandro1995.seito.model.entity.ambient

import com.leandro1995.seito.extension.isEmailFormat
import com.leandro1995.seito.fcm.authentication.AuthenticationFCM
import com.leandro1995.seito.fcm.firestore.UserFirestoreFCM

open class User(
    val name: String = "",
    val lastName: String = "",
    var email: String = "",
    val age: Int = -1,
    val sex: String = "",
    val code: String = "",
    var password: String = ""
) {

    fun loginFirebase(success: (email: String) -> Unit, error: () -> Unit) {
        AuthenticationFCM().signInWithEmailAndPassword(
            email = email, password = password, success = success, error = error
        )
    }

    fun detailFirebase(
        success: (
            name: String, lastName: String, age: Int, sex: String, code: String, teacherName: String, coins: Int
        ) -> Unit, error: () -> Unit
    ) {
        UserFirestoreFCM().whereEqualToEmail(email = email, success = success, error = error)
    }

    fun isLogin() = isEmail() && isPassword()

    fun isEmail() = email.isEmpty()

    fun isPassword() = password.isEmpty()

    fun isEmailFormat() = email.isEmailFormat()
}