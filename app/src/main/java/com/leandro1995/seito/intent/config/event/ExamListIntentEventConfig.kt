package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.ExamListIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.ExamListIntentEvent

class ExamListIntentEventConfig(private val examListIntentEventCallBack: ExamListIntentEventCallBack?) :
    IntentConfigAmbient<ExamListIntentEvent>() {

    override fun initConfig(event: ExamListIntentEvent?) {
        when (event) {
            is ExamListIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = examListIntentEventCallBack
                )
            }

            ExamListIntentEvent.ExamAdd -> {
                examListIntentEventCallBack?.examAdd()
            }

            null -> {}
        }
    }
}