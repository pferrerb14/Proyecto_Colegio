package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.SubThemeLevelIntentAction
import com.leandro1995.seito.intent.callback.action.SubThemeLevelIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class SubThemeLevelIntentActionConfig(private val subthemeLevelIntentActionCallBack: SubThemeLevelIntentActionCallBack?) :
    IntentConfigAmbient<SubThemeLevelIntentAction>() {

    override fun initConfig(event: SubThemeLevelIntentAction?) {
        if (event != null) {
            event.levelArrayList?.let {
                subthemeLevelIntentActionCallBack?.levelArrayList(levelArrayList = it)
            }
        } else {
            subthemeLevelIntentActionCallBack?.startService()
        }
    }
}