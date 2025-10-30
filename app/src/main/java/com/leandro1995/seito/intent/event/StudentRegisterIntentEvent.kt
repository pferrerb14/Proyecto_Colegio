package com.leandro1995.seito.intent.event

import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient

sealed class StudentRegisterIntentEvent {
    data class AlertMessage(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        StudentRegisterIntentEvent()

    data object MaleSelect : StudentRegisterIntentEvent()
    data object FemaleSelect : StudentRegisterIntentEvent()
    data class Loading(val loadingIntentEventAmbient: LoadingIntentEventAmbient) :
        StudentRegisterIntentEvent()

    data class LoginActivity(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        StudentRegisterIntentEvent()
}