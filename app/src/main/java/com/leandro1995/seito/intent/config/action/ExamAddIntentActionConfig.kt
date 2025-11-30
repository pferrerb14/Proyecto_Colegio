package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.ExamAddIntentAction
import com.leandro1995.seito.intent.callback.action.ExamAddIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class ExamAddIntentActionConfig(private val examAddIntentActionCallBack: ExamAddIntentActionCallBack?) :
    IntentConfigAmbient<ExamAddIntentAction>() {

    override fun initConfig(event: ExamAddIntentAction?) {
        if (event != null) {
            event.courseArrayList?.let {
                examAddIntentActionCallBack?.courseArrayList(courseArrayList = it)
            }

            event.themeArrayList?.let {
                examAddIntentActionCallBack?.themeArrayList(themeArrayList = it)
            }

            event.subThemeArrayList?.let {
                examAddIntentActionCallBack?.subThemeArrayList(subThemeArrayList = it)
            }

            event.questionArrayList?.let {
                examAddIntentActionCallBack?.questionArrayList(questionArrayList = it)
            }
            event.activateButton?.let {
                examAddIntentActionCallBack?.activateButton(isEnable = it)
            }
        } else {
            examAddIntentActionCallBack?.startService()
        }
    }
}