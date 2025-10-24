package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.LoginLoadingIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.LoginIntentEvent

class LoginIntentEventConfig(private val loginIntentEventCallBack: LoginLoadingIntentEventCallBack?) :
    IntentConfigAmbient<LoginIntentEvent>() {
    override fun initConfig(event: LoginIntentEvent?) {
        when (event) {
            LoginIntentEvent.StudentSelect -> {
                loginIntentEventCallBack?.studentSelect()
            }

            LoginIntentEvent.TeacherSelect -> {
                loginIntentEventCallBack?.teacherSelect()
            }

            LoginIntentEvent.AdminSelect -> {
                loginIntentEventCallBack?.adminSelect()
            }

            is LoginIntentEvent.AlertMessage -> {
                loginIntentEventCallBack?.alertMessage(alertMessage = event.alertMessage)
            }

            is LoginIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = loginIntentEventCallBack
                )
            }

            LoginIntentEvent.HomeActivity -> {
                loginIntentEventCallBack?.homeActivity()
            }

            null -> {}
        }
    }
}