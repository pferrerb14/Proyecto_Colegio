package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.ExamGraphicIntentAction
import com.leandro1995.seito.intent.callback.action.ExamGraphicIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class ExamGraphicIntentActionConfig(private val examGraphicIntentActionCallBack: ExamGraphicIntentActionCallBack?) :
    IntentConfigAmbient<ExamGraphicIntentAction>() {

    override fun initConfig(event: ExamGraphicIntentAction?) {

        if (event != null) {
            event.noteArrayList?.let {
                examGraphicIntentActionCallBack?.noteArrayList(noteArrayList = it)
            }
        } else {
            examGraphicIntentActionCallBack?.startService()
        }
    }
}