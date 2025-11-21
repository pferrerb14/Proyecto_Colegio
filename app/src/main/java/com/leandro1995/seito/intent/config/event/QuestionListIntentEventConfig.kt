package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.QuestionListIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.QuestionListIntentEvent

class QuestionListIntentEventConfig(private val questionListIntentEventCallBack: QuestionListIntentEventCallBack?) :
    IntentConfigAmbient<QuestionListIntentEvent>() {

    override fun initConfig(event: QuestionListIntentEvent?) {
        when (event) {
            QuestionListIntentEvent.QuestionAdd -> {
                questionListIntentEventCallBack?.questionAdd()
            }

            is QuestionListIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = questionListIntentEventCallBack
                )
            }

            is QuestionListIntentEvent.AlertMessage -> {
                questionListIntentEventCallBack?.alertMessage(alertMessage = event.alertMessage)
            }

            null -> {}
        }
    }
}