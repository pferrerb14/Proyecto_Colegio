package com.leandro1995.seito.intent.event

import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient

sealed class CodeVerifyIntentEvent {
    data class AlertMessage(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        CodeVerifyIntentEvent()

    data class Loading(val loadingIntentEventAmbient: LoadingIntentEventAmbient) :
        CodeVerifyIntentEvent()
}