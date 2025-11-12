package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.SubThemeLevelAddIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.SubThemeLevelAddIntentEvent

class SubThemeLevelAddIntentEventConfig(private val subThemeLevelAddIntentEventCallBack: SubThemeLevelAddIntentEventCallBack?) :
    IntentConfigAmbient<SubThemeLevelAddIntentEvent>() {

    override fun initConfig(event: SubThemeLevelAddIntentEvent?) {
        when (event) {
            is SubThemeLevelAddIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = subThemeLevelAddIntentEventCallBack
                )
            }

            is SubThemeLevelAddIntentEvent.AlertMessage -> {
                subThemeLevelAddIntentEventCallBack?.message(alertMessage = event.alertMessage)
            }

            null -> {}
        }
    }
}