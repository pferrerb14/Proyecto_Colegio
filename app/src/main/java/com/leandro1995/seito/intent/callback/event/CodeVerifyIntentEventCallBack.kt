package com.leandro1995.seito.intent.callback.event

import com.leandro1995.seito.intent.callback.ambient.LoadingIntentCallBackAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Teacher

interface CodeVerifyIntentEventCallBack : LoadingIntentCallBackAmbient {

    fun alertMessage(alertMessage: AlertMessage)
    fun studentRegisterFragment(teacher: Teacher)
}