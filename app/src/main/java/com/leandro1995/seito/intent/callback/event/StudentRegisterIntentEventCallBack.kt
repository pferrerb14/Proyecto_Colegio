package com.leandro1995.seito.intent.callback.event

import com.leandro1995.seito.model.design.AlertMessage

interface StudentRegisterIntentEventCallBack {
    fun alertMessage(alertMessage: AlertMessage)
    fun maleSelect()
    fun femaleSelect()
}