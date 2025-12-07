package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.ExamGraphicIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.ExamGraphicIntentEvent

class ExamGraphicIntentEventConfig(private val examGraphicIntentEventCallBack: ExamGraphicIntentEventCallBack?) :
    IntentConfigAmbient<ExamGraphicIntentEvent>() {

    override fun initConfig(event: ExamGraphicIntentEvent?) {
        when (event) {
            is ExamGraphicIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = examGraphicIntentEventCallBack
                )
            }

            is ExamGraphicIntentEvent.AlertMessage -> {
                examGraphicIntentEventCallBack?.alertMessage(alertMessage = event.alertMessage)
            }

            null -> {}
        }
    }
}