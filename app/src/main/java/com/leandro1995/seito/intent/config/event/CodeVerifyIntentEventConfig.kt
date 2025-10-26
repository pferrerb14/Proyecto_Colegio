package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.CodeVerifyIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.CodeVerifyIntentEvent

class CodeVerifyIntentEventConfig(private val codeVerifyIntentEventCallBack: CodeVerifyIntentEventCallBack?) :
    IntentConfigAmbient<CodeVerifyIntentEvent>() {

    override fun initConfig(event: CodeVerifyIntentEvent?) {
        when (event) {
            is CodeVerifyIntentEvent.AlertMessage -> {
                codeVerifyIntentEventCallBack?.alertMessage(alertMessage = event.alertMessage)
            }

            is CodeVerifyIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = codeVerifyIntentEventCallBack
                )
            }

            is CodeVerifyIntentEvent.StudentRegisterFragment -> {
                codeVerifyIntentEventCallBack?.studentRegisterFragment(teacher = event.teacher)
            }

            null -> {}
        }
    }
}