package com.leandro1995.seito.intent.callback.event

import com.leandro1995.seito.intent.callback.ambient.LoadingIntentCallBackAmbient
import com.leandro1995.seito.model.design.AlertMessage

interface LoginLoadingIntentEventCallBack : LoadingIntentCallBackAmbient {

    fun studentSelect()
    fun teacherSelect()
    fun alertMessage(alertMessage: AlertMessage)
    fun studentHomeActivity()
    fun teacherHomeActivity()
    fun studentRegister()
}