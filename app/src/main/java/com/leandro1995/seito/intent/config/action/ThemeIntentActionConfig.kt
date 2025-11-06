package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.ThemeIntentAction
import com.leandro1995.seito.intent.callback.action.ThemeIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class ThemeIntentActionConfig(private val themeIntentActionCallBack: ThemeIntentActionCallBack?) :
    IntentConfigAmbient<ThemeIntentAction>() {

    override fun initConfig(event: ThemeIntentAction?) {
        if (event != null) {

        } else {
            themeIntentActionCallBack?.startService()
        }
    }
}