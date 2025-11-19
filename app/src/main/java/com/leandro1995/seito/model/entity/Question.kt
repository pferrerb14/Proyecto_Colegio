package com.leandro1995.seito.model.entity

class Question(
    var imageUrl: String = "",
    var name: String = "",
    var optionArrayList: ArrayList<Option> = arrayListOf(),
    var answer: String = "",
    val isType: Boolean = false,
    var coin: Int = -1
) {

    fun isName() = name.isEmpty()

    fun isOptionArrayList() = optionArrayList.isEmpty()
}