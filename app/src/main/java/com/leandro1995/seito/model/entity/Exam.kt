package com.leandro1995.seito.model.entity

class Exam(
    val id: String = "", var name: String = ""
) {
    fun isNameEmpty() = name.isEmpty()
}