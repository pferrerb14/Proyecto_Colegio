package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.AssistantIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.AssistantIntentEvent

class AssistantIntentEventConfig(private val assistantIntentEventCallBack: AssistantIntentEventCallBack) :
    IntentConfigAmbient<AssistantIntentEvent>() {

    override fun initConfig(event: AssistantIntentEvent?) {
        when (event) {
            is AssistantIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventCallBack = assistantIntentEventCallBack,
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient
                )
            }

            null -> {}
        }
    }
}