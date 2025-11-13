package com.leandro1995.seito.intent.event

import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient

sealed class LoginIntentEvent {
    data object StudentSelect : LoginIntentEvent()
    data object TeacherSelect : LoginIntentEvent()
    data object StudentHomeActivity : LoginIntentEvent()
    data object TeacherHomeActivity : LoginIntentEvent()
    data object StudentRegister : LoginIntentEvent()
    data class AlertMessage(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        LoginIntentEvent()

    data class Loading(val loadingIntentEventAmbient: LoadingIntentEventAmbient) :
        LoginIntentEvent()
}