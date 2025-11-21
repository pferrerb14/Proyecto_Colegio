package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.SubThemeLevelIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.SubThemeLevelIntentEvent

class SubThemeLevelIntentEventConfig(private val subThemeLevelIntentEventCallBack: SubThemeLevelIntentEventCallBack?) :
    IntentConfigAmbient<SubThemeLevelIntentEvent>() {

    override fun initConfig(event: SubThemeLevelIntentEvent?) {
        when (event) {
            is SubThemeLevelIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = subThemeLevelIntentEventCallBack
                )
            }

            is SubThemeLevelIntentEvent.AlertMessage -> {
                subThemeLevelIntentEventCallBack?.alertMessage(alertMessage = event.alertMessage)
            }

            null -> {}
        }
    }
}