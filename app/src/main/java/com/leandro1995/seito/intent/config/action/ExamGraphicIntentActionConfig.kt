package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.ExamGraphicIntentAction
import com.leandro1995.seito.intent.callback.event.ExamGraphicIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class ExamGraphicIntentActionConfig(private val examGraphicIntentEventCallBack: ExamGraphicIntentEventCallBack?) :
    IntentConfigAmbient<ExamGraphicIntentAction>() {

    override fun initConfig(event: ExamGraphicIntentAction?) {

        if (event != null) {

        } else {

        }
    }
}