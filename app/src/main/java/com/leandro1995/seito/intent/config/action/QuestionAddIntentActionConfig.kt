package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.QuestionAddIntentAction
import com.leandro1995.seito.intent.callback.action.QuestionAddIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class QuestionAddIntentActionConfig(private val questionAddIntentActionCallBack: QuestionAddIntentActionCallBack) :
    IntentConfigAmbient<QuestionAddIntentAction>() {

    override fun initConfig(event: QuestionAddIntentAction?) {

    }
}