package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.QuestionListIntentAction
import com.leandro1995.seito.intent.callback.action.QuestionListIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class QuestionListIntentActionConfig(private val questionListIntentActionCallBack: QuestionListIntentActionCallBack) :
    IntentConfigAmbient<QuestionListIntentAction>() {

    override fun initConfig(event: QuestionListIntentAction?) {
        if (event != null) {

        } else {

        }
    }
}