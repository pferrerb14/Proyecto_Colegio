package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.QuestionAnswerIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.QuestionAnswerIntentEvent

class QuestionAnswerIntentEventConfig(private val questionAnswerIntentEventCallBack: QuestionAnswerIntentEventCallBack?) :
    IntentConfigAmbient<QuestionAnswerIntentEvent>() {

    override fun initConfig(event: QuestionAnswerIntentEvent?) {
        when (event) {

            is QuestionAnswerIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = questionAnswerIntentEventCallBack
                )
            }

            is QuestionAnswerIntentEvent.AlertMessage -> {
                questionAnswerIntentEventCallBack?.alertMessage(alertMessage = event.alertMessage)
            }

            is QuestionAnswerIntentEvent.CompleteQuestionMessage -> {
                questionAnswerIntentEventCallBack?.completeQuestionMessage(
                    alertMessage = event.alertMessage, updateCoin = event.updateCoin
                )
            }

            null -> {}
        }
    }
}