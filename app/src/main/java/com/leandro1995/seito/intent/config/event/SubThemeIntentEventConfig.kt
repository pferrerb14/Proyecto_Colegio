package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.SubThemeIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.SubThemeIntentEvent

class SubThemeIntentEventConfig(private val subThemeIntentEventCallBack: SubThemeIntentEventCallBack?) :
    IntentConfigAmbient<SubThemeIntentEvent>() {

    override fun initConfig(event: SubThemeIntentEvent?) {
        when (event) {
            is SubThemeIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = subThemeIntentEventCallBack
                )
            }

            null -> {}
        }
    }
}