package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.StudentListIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.StudentListIntentEvent

class StudentListIntentEventConfig(private val studentListIntentEventCallBack: StudentListIntentEventCallBack?) :
    IntentConfigAmbient<StudentListIntentEvent>() {

    override fun initConfig(event: StudentListIntentEvent?) {
        when (event) {
            is StudentListIntentEvent.Loading -> {
                loadingIntentEventAmbient(
                    loadingIntentEventAmbient = event.loadingIntentEventAmbient,
                    loadingIntentEventCallBack = studentListIntentEventCallBack
                )
            }

            null -> {}
        }
    }
}