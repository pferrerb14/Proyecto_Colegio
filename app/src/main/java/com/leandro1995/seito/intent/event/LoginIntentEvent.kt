package com.leandro1995.seito.intent.event

sealed class LoginIntentEvent {
    data object StudentSelect : LoginIntentEvent()
    data object TeacherSelect : LoginIntentEvent()
    data object AdminSelect : LoginIntentEvent()
}