package com.leandro1995.seito.intent.callback.event

import com.leandro1995.seito.intent.callback.ambient.LoadingIntentCallBackAmbient

interface ExamListIntentEventCallBack : LoadingIntentCallBackAmbient {

    fun examAdd()
    fun alertMessage(alertMessage: com.leandro1995.seito.model.design.AlertMessage)
}