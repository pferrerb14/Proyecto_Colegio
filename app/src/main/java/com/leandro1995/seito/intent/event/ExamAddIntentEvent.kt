package com.leandro1995.seito.intent.event

import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient

sealed class ExamAddIntentEvent {
    data class Loading(val loadingIntentEventAmbient: LoadingIntentEventAmbient) :
        ExamAddIntentEvent()

    data class AlertMessage(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        ExamAddIntentEvent()

    data class RegisterAlertMessage(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        ExamAddIntentEvent()
}