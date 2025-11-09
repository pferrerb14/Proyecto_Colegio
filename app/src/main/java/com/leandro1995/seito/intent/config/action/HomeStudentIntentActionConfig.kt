package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.HomeStudentIntentAction
import com.leandro1995.seito.intent.callback.action.HomeStudentIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class HomeStudentIntentActionConfig(private val homeStudentIntentActionCallBack: HomeStudentIntentActionCallBack?) :
    IntentConfigAmbient<HomeStudentIntentAction>() {

    override fun initConfig(event: HomeStudentIntentAction?) {
        if (event != null) {
            event.student?.let {
                homeStudentIntentActionCallBack?.studentDetail(student = it)
            }

            event.courseVideoArrayList?.let {
                homeStudentIntentActionCallBack?.courseVideoArrayList(courseArrayList = it)
            }

            event.courseArrayList?.let {
                homeStudentIntentActionCallBack?.courseArrayList(courseArrayList = it)
            }
        } else {
            homeStudentIntentActionCallBack?.getProtoDataStore()
        }
    }
}