package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.ThemeIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.ThemeIntentEvent

class ThemeIntentEventConfig(private val themeIntentEventCallBack: ThemeIntentEventCallBack?) :
    IntentConfigAmbient<ThemeIntentEvent>() {

    override fun initConfig(event: ThemeIntentEvent?) {
        when (event) {
            is ThemeIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = themeIntentEventCallBack
                )
            }

            null -> {}
        }
    }
}