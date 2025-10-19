package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.LoginIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.LoginIntentEvent

class LoginIntentEventConfig(private val loginIntentEventCallBack: LoginIntentEventCallBack) :
    IntentConfigAmbient<LoginIntentEvent>() {

    override fun initConfig(event: LoginIntentEvent) {
        when (event) { }
    }
}