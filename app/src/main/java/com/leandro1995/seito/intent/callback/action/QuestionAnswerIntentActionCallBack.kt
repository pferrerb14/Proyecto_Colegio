package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Question

interface QuestionAnswerIntentActionCallBack {

    fun startView()
    fun questionArrayList(questionArrayList: ArrayList<Question>)
    fun page(position: Int)
    fun coin(coin: Int)
    fun isEnableNextButton(isEnable: Boolean)
}