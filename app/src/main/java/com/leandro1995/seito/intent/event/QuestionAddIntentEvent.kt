package com.leandro1995.seito.intent.event

sealed class QuestionAddIntentEvent {
    data class AlertMessage(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        QuestionAddIntentEvent()

    data class OptionAddBottomSheet(val isAnswer: Boolean) : QuestionAddIntentEvent()
}