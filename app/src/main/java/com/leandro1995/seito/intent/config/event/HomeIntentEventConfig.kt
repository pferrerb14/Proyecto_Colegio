package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.HomeIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.HomeIntentEvent

class HomeIntentEventConfig(private val homeIntentEventCallBack: HomeIntentEventCallBack?) :
    IntentConfigAmbient<HomeIntentEvent>() {

    override fun initConfig(event: HomeIntentEvent?) {
        when (event) {
            is HomeIntentEvent.HomeView -> {
                homeIntentEventCallBack?.homeView(
                    idBottomNavigation = event.idBottomNavigation,
                    idGraphNavigation = event.idGraphNavigation
                )
            }

            null -> {}
        }
    }
}