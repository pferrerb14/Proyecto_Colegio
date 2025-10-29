package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.StudentRegisterIntentAction
import com.leandro1995.seito.intent.callback.action.StudentRegisterIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class StudentRegisterIntentActionConfig(private val studentRegisterIntentActionCallBack: StudentRegisterIntentActionCallBack?) :
    IntentConfigAmbient<StudentRegisterIntentAction>() {

    override fun initConfig(event: StudentRegisterIntentAction?) {
        event?.let {
            if (it.nameTeacher.isNotEmpty()) {
                studentRegisterIntentActionCallBack?.nameTeacher(fullName = it.nameTeacher)
            }
        }
    }
}