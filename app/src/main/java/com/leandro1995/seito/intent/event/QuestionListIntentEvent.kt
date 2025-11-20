package com.leandro1995.seito.intent.event

sealed class QuestionListIntentEvent {
    data object QuestionAdd : QuestionListIntentEvent()
}