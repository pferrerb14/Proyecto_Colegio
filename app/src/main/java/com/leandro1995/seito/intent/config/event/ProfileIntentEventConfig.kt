package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.ProfileIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.ProfileIntentEvent

class ProfileIntentEventConfig(private val profileIntentEventCallBack: ProfileIntentEventCallBack?) :
    IntentConfigAmbient<ProfileIntentEvent>() {

    override fun initConfig(event: ProfileIntentEvent?) {
        when (event) {
            ProfileIntentEvent.CleanProtoDataStore -> {
                profileIntentEventCallBack?.cleanProtoDataStore()
            }

            null -> {}
        }
    }
}