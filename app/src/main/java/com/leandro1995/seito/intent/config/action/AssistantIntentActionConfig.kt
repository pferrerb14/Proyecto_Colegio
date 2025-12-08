package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.AssistantIntentAction
import com.leandro1995.seito.intent.callback.action.AssistantIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class AssistantIntentActionConfig(private val assistantIntentActionCallBack: AssistantIntentActionCallBack?) :
    IntentConfigAmbient<AssistantIntentAction>() {

    override fun initConfig(event: AssistantIntentAction?) {
        if (event != null) {
            event.fullName?.let {
                assistantIntentActionCallBack?.fullName(fullName = it)
            }
        } else {
            assistantIntentActionCallBack?.startList()
        }
    }
}