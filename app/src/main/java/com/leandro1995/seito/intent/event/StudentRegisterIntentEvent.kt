package com.leandro1995.seito.intent.event

sealed class StudentRegisterIntentEvent {
    data class AlertMessage(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        StudentRegisterIntentEvent()
}