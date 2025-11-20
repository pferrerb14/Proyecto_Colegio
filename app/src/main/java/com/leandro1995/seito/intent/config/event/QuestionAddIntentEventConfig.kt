package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.QuestionAddIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.QuestionAddIntentEvent

class QuestionAddIntentEventConfig(private val questionAddIntentEventCallBack: QuestionAddIntentEventCallBack?) :
    IntentConfigAmbient<QuestionAddIntentEvent>() {

    override fun initConfig(event: QuestionAddIntentEvent?) {
        when (event) {
            is QuestionAddIntentEvent.AlertMessage -> {
                questionAddIntentEventCallBack?.alertMessage(event.alertMessage)
            }

            is QuestionAddIntentEvent.OptionAddBottomSheet -> {
                questionAddIntentEventCallBack?.optionAddBottomSheet(isAnswer = event.isAnswer)
            }

            is QuestionAddIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = questionAddIntentEventCallBack
                )
            }

            null -> {}
        }
    }
}