package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.LoginIntentAction
import com.leandro1995.seito.intent.callback.action.LoginIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class LoginIntentActionConfig(private val loginIntentActionCallBack: LoginIntentActionCallBack?) :
    IntentConfigAmbient<LoginIntentAction>() {
    override fun initConfig(event: LoginIntentAction?) {

        when (event) {
            is LoginIntentAction -> {
                event.loading?.let {
                    loginIntentActionCallBack?.loading(loading = it)
                }
            }

            null -> {}
        }
    }
}