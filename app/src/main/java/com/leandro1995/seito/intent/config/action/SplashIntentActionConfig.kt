package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.SplashIntentAction
import com.leandro1995.seito.intent.callback.action.SplashIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class SplashIntentActionConfig(private val splashIntentActionCallBack: SplashIntentActionCallBack?) :
    IntentConfigAmbient<SplashIntentAction>() {

    override fun initConfig(event: SplashIntentAction?) {
        event?.let {
            if (it.isLoginDirect) {
                splashIntentActionCallBack?.login()
            }

            if (it.isStudentHomeDirect) {
                splashIntentActionCallBack?.studentHome()
            }

            if (it.isTeacherHomeDirect) {
                splashIntentActionCallBack?.teacherHome()
            }

            if (it.isValidationDirect) {
                splashIntentActionCallBack?.validationDirect()
            }
        }
    }
}