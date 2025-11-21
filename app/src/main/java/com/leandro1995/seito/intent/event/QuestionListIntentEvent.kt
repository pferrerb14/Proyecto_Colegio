package com.leandro1995.seito.intent.event

import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient

sealed class QuestionListIntentEvent {
    data object QuestionAdd : QuestionListIntentEvent()
    data class Loading(val loadingIntentEventAmbient: LoadingIntentEventAmbient) :
        QuestionListIntentEvent()

    data class AlertMessage(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        QuestionListIntentEvent()
}