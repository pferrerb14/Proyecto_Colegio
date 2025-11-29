package com.leandro1995.seito.model.entity

class Exam(
    val id: String = "",
    var name: String = "",
    var questionArrayList: ArrayList<Question> = arrayListOf()
)