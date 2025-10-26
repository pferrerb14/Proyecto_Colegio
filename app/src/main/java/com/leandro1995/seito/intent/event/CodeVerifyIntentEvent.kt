package com.leandro1995.seito.intent.event

sealed class CodeVerifyIntentEvent {
    data class AlertMessage(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        CodeVerifyIntentEvent()
}