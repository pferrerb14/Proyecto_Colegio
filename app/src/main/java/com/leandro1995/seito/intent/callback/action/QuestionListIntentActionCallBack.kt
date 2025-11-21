package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Question

interface QuestionListIntentActionCallBack {

    fun questionArrayList(questionArrayList: ArrayList<Question>)
    fun startService()
}