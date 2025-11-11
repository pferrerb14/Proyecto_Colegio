package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.ThemeAddIntentAction
import com.leandro1995.seito.intent.callback.action.ThemeAddIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class ThemeAddIntentActionConfig(private val themeAddIntentActionCallBack: ThemeAddIntentActionCallBack?) :
    IntentConfigAmbient<ThemeAddIntentAction>() {

    override fun initConfig(event: ThemeAddIntentAction?) {
        if (event != null) {
            event.themArrayList?.let {
                themeAddIntentActionCallBack?.themeArrayList(themeArrayList = it)
            }
        } else {
            themeAddIntentActionCallBack?.startService()
        }
    }
}