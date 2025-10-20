package com.leandro1995.seito.intent.callback.event

import com.leandro1995.seito.model.design.AlertMessage

interface LoginIntentEventCallBack {

    fun studentSelect()
    fun teacherSelect()
    fun adminSelect()
    fun alertMessage(alertMessage: AlertMessage)
}