package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.ThemeAddIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.ThemeAddIntentEvent

class ThemeAddIntentEventConfig(private val themeAddIntentEventCallBack: ThemeAddIntentEventCallBack?) :
    IntentConfigAmbient<ThemeAddIntentEvent>() {

    override fun initConfig(event: ThemeAddIntentEvent?) {
        when (event) {
            is ThemeAddIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = themeAddIntentEventCallBack
                )
            }

            ThemeAddIntentEvent.TopicEditorBottomSheet -> {
                themeAddIntentEventCallBack?.topicEditorBottomSheet()
            }

            is ThemeAddIntentEvent.AlertMessage -> {
                themeAddIntentEventCallBack?.message(alertMessage = event.alertMessage)
            }

            null -> {}
        }
    }
}