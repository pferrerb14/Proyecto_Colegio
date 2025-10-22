package com.leandro1995.seito.model.entity.ambient

import com.leandro1995.seito.extension.isEmailFormat

class User(var email: String = "", var password: String = "") {

    fun isLogin() = isEmail() && isPassword()

    fun isEmail() = email.isEmpty()

    fun isPassword() = password.isEmpty()

    fun isEmailFormat() = email.isEmailFormat()
}