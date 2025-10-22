package com.leandro1995.seito.model.entity.ambient

class User(var email: String = "", var password: String = "") {

    fun isLogin() = isEmail() && isPassword()

    fun isEmail() = email.isEmpty()

    fun isPassword() = password.isEmpty()
}