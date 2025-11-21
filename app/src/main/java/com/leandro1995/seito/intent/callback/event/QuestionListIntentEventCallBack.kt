package com.leandro1995.seito.intent.callback.event

import com.leandro1995.seito.intent.callback.ambient.LoadingIntentCallBackAmbient
import com.leandro1995.seito.model.design.AlertMessage

interface QuestionListIntentEventCallBack : LoadingIntentCallBackAmbient {
    fun questionAdd()
    fun alertMessage(alertMessage: AlertMessage)
}