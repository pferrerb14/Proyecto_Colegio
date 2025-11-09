package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.HomeStudentIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.HomeStudentIntentEvent

class HomeStudentIntentEventConfig(private val homeStudentIntentEventCallBack: HomeStudentIntentEventCallBack?) :
    IntentConfigAmbient<HomeStudentIntentEvent>() {

    override fun initConfig(event: HomeStudentIntentEvent?) {
        when (event) {
            is HomeStudentIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = homeStudentIntentEventCallBack
                )
            }

            is HomeStudentIntentEvent.VideoDetail -> {
                homeStudentIntentEventCallBack?.videoDetail(courseArrayList = event.courseArrayList)
            }

            null -> {}
        }
    }
}