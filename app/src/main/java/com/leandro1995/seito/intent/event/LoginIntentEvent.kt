package com.leandro1995.seito.intent.event

sealed class LoginIntentEvent {
    data object StudentSelect : LoginIntentEvent()
    data object TeacherSelect : LoginIntentEvent()
    data object AdminSelect : LoginIntentEvent()
    data class AlertMessage(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        LoginIntentEvent()
}