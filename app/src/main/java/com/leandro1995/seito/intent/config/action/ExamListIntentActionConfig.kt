package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.ExamListIntentAction
import com.leandro1995.seito.intent.callback.action.ExamListIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class ExamListIntentActionConfig(private val examListIntentActionCallBack: ExamListIntentActionCallBack?) :
    IntentConfigAmbient<ExamListIntentAction>() {

    override fun initConfig(event: ExamListIntentAction?) {
        if (event != null) {
            event.examArrayList?.let {
                examListIntentActionCallBack?.examArrayList(examArrayList = it)
            }
        } else {
            examListIntentActionCallBack?.startService()
        }
    }
}