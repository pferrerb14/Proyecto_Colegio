package com.leandro1995.seito.model.entity

import com.leandro1995.seito.config.Setting

class Question(
    var imageUrl: String = "",
    var name: String = "",
    var optionArrayList: ArrayList<Option> = arrayListOf(),
    var answer: String = "",
    var isType: Boolean = false,
    var coins: Int = -1
) {

    fun isName() = name.isEmpty()

    fun isOptionArrayList() = optionArrayList.isEmpty()

    fun optionLength() = optionArrayList.size != Setting.OPTION_LENGTH

    fun isCoin() = coins == -1

    fun isAnswerSelect() = optionArrayList.none { it.isAnswer }

    fun answerUpdate() {
        answer = optionArrayList.firstOrNull { it.isAnswer }?.name.orEmpty()
    }
}