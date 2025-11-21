package com.leandro1995.seito.intent.callback.event

import com.leandro1995.seito.intent.callback.ambient.LoadingIntentCallBackAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Question

interface SubThemeLevelIntentEventCallBack : LoadingIntentCallBackAmbient {

    fun alertMessage(alertMessage: AlertMessage)
    fun questionAnswer(questionArrayList: ArrayList<Question>)
}