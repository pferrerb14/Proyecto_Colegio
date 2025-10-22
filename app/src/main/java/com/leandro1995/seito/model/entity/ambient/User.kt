package com.leandro1995.seito.model.entity.ambient

import com.leandro1995.seito.extension.isEmailFormat
import com.leandro1995.seito.fcm.authentication.AuthenticationFCM

class User(var email: String = "", var password: String = "") {

    fun loginFirebase(success: (email: String) -> Unit, error: () -> Unit) {
        AuthenticationFCM().signInWithEmailAndPassword(
            email = email, password = password, success = success, error = error
        )
    }

    fun isLogin() = isEmail() && isPassword()

    fun isEmail() = email.isEmpty()

    fun isPassword() = password.isEmpty()

    fun isEmailFormat() = email.isEmailFormat()
}