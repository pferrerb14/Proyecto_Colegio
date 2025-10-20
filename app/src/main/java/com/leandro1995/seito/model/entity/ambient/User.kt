package com.leandro1995.seito.model.entity.ambient

class User(var email: String = "", var password: String = "") {

    fun isLogin() = isEmail() && isPassword()

    private fun isEmail() = email.isEmpty()

    private fun isPassword() = password.isEmpty()
}