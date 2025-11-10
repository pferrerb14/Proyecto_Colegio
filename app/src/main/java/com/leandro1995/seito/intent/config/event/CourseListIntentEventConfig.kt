package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.CourseListIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.CourseListIntentEvent

class CourseListIntentEventConfig(private val courseListIntentEventCallBack: CourseListIntentEventCallBack?) :
    IntentConfigAmbient<CourseListIntentEvent>() {

    override fun initConfig(event: CourseListIntentEvent?) {
        when (event) {
            is CourseListIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = courseListIntentEventCallBack
                )
            }

            null -> {}
        }
    }
}