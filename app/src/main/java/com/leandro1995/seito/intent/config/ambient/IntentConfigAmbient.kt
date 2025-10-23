package com.leandro1995.seito.intent.config.ambient

import com.leandro1995.seito.intent.callback.ambient.LoadingIntentCallBackAmbient
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient

abstract class IntentConfigAmbient<AE> {

    abstract fun initConfig(event: AE?)

    fun loadingIntentEventAmbient(
        loadingIntentEventAmbient: LoadingIntentEventAmbient,
        loadingIntentEventCallBack: LoadingIntentCallBackAmbient?
    ) {
        when (loadingIntentEventAmbient) {
            is LoadingIntentEventAmbient.Loading -> {
                loadingIntentEventCallBack?.loading(loading = loadingIntentEventAmbient.loading)
            }
        }
    }
}