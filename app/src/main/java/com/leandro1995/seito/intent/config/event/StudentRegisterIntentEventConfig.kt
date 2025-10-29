package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.StudentRegisterIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.StudentRegisterIntentEvent

class StudentRegisterIntentEventConfig(private val studentRegisterIntentEventCallBack: StudentRegisterIntentEventCallBack?) :
    IntentConfigAmbient<StudentRegisterIntentEvent>() {

    override fun initConfig(event: StudentRegisterIntentEvent?) {
        when (event) {
            is StudentRegisterIntentEvent.AlertMessage -> {
                studentRegisterIntentEventCallBack?.alertMessage(alertMessage = event.alertMessage)
            }

            StudentRegisterIntentEvent.FemaleSelect -> {
                studentRegisterIntentEventCallBack?.femaleSelect()
            }

            StudentRegisterIntentEvent.MaleSelect -> {
                studentRegisterIntentEventCallBack?.maleSelect()
            }

            is StudentRegisterIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = studentRegisterIntentEventCallBack
                )
            }

            null -> {}
        }
    }
}