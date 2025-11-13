package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.StudentListIntentAction
import com.leandro1995.seito.intent.callback.action.StudentListIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class StudentListIntentActionConfig(private val studentListIntentActionCallBack: StudentListIntentActionCallBack?) :
    IntentConfigAmbient<StudentListIntentAction>() {

    override fun initConfig(event: StudentListIntentAction?) {
        if (event != null) {

        } else {
            studentListIntentActionCallBack?.startService()
        }
    }
}