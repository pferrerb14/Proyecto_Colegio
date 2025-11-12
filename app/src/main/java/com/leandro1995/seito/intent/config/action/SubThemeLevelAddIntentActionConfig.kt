package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.SubThemeLevelAddIntentAction
import com.leandro1995.seito.intent.callback.action.SubThemeLevelAddIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class SubThemeLevelAddIntentActionConfig(private val subThemeLevelAddIntentActionCallBack: SubThemeLevelAddIntentActionCallBack?) :
    IntentConfigAmbient<SubThemeLevelAddIntentAction>() {

    override fun initConfig(event: SubThemeLevelAddIntentAction?) {
        if (event != null) {
            event.levelArrayList?.let {
                subThemeLevelAddIntentActionCallBack?.levelArrayList(
                    levelArrayList = it, isShowButton = event.isShowButton
                )
            }
        } else {
            subThemeLevelAddIntentActionCallBack?.starService()
        }
    }
}