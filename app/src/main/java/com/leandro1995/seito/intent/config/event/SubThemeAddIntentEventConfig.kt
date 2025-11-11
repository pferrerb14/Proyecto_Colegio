package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.SubThemeAddIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.SubThemeAddIntentEvent

class SubThemeAddIntentEventConfig(private val subThemeAddIntentEventCallBack: SubThemeAddIntentEventCallBack?) :
    IntentConfigAmbient<SubThemeAddIntentEvent>() {

    override fun initConfig(event: SubThemeAddIntentEvent?) {
        when (event) {
            is SubThemeAddIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = subThemeAddIntentEventCallBack
                )
            }

            SubThemeAddIntentEvent.TopicEditorBottomSheet -> {
                subThemeAddIntentEventCallBack?.topicEditorBottomSheet()
            }

            null -> {}
        }
    }
}