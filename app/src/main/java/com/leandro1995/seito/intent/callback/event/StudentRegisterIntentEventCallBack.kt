package com.leandro1995.seito.intent.callback.event

import com.leandro1995.seito.intent.callback.ambient.LoadingIntentCallBackAmbient
import com.leandro1995.seito.model.design.AlertMessage

interface StudentRegisterIntentEventCallBack : LoadingIntentCallBackAmbient {
    fun alertMessage(alertMessage: AlertMessage)
    fun maleSelect()
    fun femaleSelect()
    fun loginActivity(alertMessage: AlertMessage)
}