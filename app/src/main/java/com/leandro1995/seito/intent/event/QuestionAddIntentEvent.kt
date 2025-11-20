package com.leandro1995.seito.intent.event

import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient

sealed class QuestionAddIntentEvent {
    data class AlertMessage(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        QuestionAddIntentEvent()

    data class OptionAddBottomSheet(val isAnswer: Boolean) : QuestionAddIntentEvent()
    data class Loading(val loadingIntentEventAmbient: LoadingIntentEventAmbient) :
        QuestionAddIntentEvent()
}