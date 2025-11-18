package com.leandro1995.seito.model.entity

class Question(
    var imageUrl: String = "",
    var name: String = "",
    var optionArrayList: ArrayList<String> = arrayListOf(),
    var answer: String = "",
    val isType: Boolean = false,
    var coin: Int = -1
)