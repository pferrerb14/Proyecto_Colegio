package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.ProfileIntentAction
import com.leandro1995.seito.intent.callback.action.ProfileIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class ProfileIntentActionConfig(private val profileIntentActionCallBack: ProfileIntentActionCallBack?) :
    IntentConfigAmbient<ProfileIntentAction>() {

    override fun initConfig(event: ProfileIntentAction?) {
        if (event != null) {
            event.student?.let {
                profileIntentActionCallBack?.studentView(student = it)
            }
        } else {
            profileIntentActionCallBack?.getProtoDataStore()
        }
    }
}