package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.ExerciseGraphicIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.ExerciseGraphicIntentEvent

class ExerciseGraphicIntentEventConfig(private val exerciseGraphicIntentEventCallBack: ExerciseGraphicIntentEventCallBack?) :
    IntentConfigAmbient<ExerciseGraphicIntentEvent>() {

    override fun initConfig(event: ExerciseGraphicIntentEvent?) {
        when (event) {
            is ExerciseGraphicIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = exerciseGraphicIntentEventCallBack
                )
            }

            is ExerciseGraphicIntentEvent.AlertMessage -> {
                exerciseGraphicIntentEventCallBack?.alertMessage(alertMessage = event.alertMessage)
            }

            null -> {}
        }
    }
}