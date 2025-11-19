package com.leandro1995.seito.model.entity

import com.leandro1995.seito.config.Setting

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

    fun optionLength() = optionArrayList.size != Setting.OPTION_LENGTH

    fun isCoin() = coin == -1

    fun isAnswerSelect() = optionArrayList.none { it.isAnswer }
}