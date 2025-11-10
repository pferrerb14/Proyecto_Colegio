package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.CourseListIntentAction
import com.leandro1995.seito.intent.callback.action.CourseListIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class CourseListIntentActionConfig(private var courseListIntentActionCallBack: CourseListIntentActionCallBack?) :
    IntentConfigAmbient<CourseListIntentAction>() {

    override fun initConfig(event: CourseListIntentAction?) {
        if (event != null) {

        } else {
            courseListIntentActionCallBack?.startService()
        }
    }
}