package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.QuestionAnswerIntentAction
import com.leandro1995.seito.intent.callback.action.QuestionAnswerIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class QuestionAnswerIntentActionConfig(private val questionAnswerIntentActionCallBack: QuestionAnswerIntentActionCallBack?) :
    IntentConfigAmbient<QuestionAnswerIntentAction>() {

    override fun initConfig(event: QuestionAnswerIntentAction?) {
        if (event != null) {
            event.questionArrayList?.let {
                questionAnswerIntentActionCallBack?.questionArrayList(questionArrayList = it)
            }
        } else {
            questionAnswerIntentActionCallBack?.startView()
        }
    }
}