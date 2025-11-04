package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.HomeIntentAction
import com.leandro1995.seito.intent.callback.action.HomeIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class HomeIntentActionConfig(private val homeIntentActionCallBack: HomeIntentActionCallBack?) :
    IntentConfigAmbient<HomeIntentAction>() {

    override fun initConfig(event: HomeIntentAction?) {

    }
}