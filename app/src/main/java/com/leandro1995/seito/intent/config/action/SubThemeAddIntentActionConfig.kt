package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.SubThemeAddIntentAction
import com.leandro1995.seito.intent.callback.action.SubThemeAddIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class SubThemeAddIntentActionConfig(private val subThemeAddIntentActionCallBack: SubThemeAddIntentActionCallBack?) :
    IntentConfigAmbient<SubThemeAddIntentAction>() {

    override fun initConfig(event: SubThemeAddIntentAction?) {
        if (event != null) {
            event.subThemeArrayList?.let {
                subThemeAddIntentActionCallBack?.subThemeList(subThemeArrayList = it)
            }
        } else {
            subThemeAddIntentActionCallBack?.startService()
        }
    }
}