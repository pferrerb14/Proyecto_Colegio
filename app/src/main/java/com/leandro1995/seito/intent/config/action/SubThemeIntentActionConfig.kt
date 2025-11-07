package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.SubThemeIntentAction
import com.leandro1995.seito.intent.callback.action.SubThemeIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class SubThemeIntentActionConfig(private val subThemeIntentActionCallBack: SubThemeIntentActionCallBack?) :
    IntentConfigAmbient<SubThemeIntentAction>() {

    override fun initConfig(event: SubThemeIntentAction?) {

    }
}