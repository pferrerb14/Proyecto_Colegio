package com.leandro1995.seito.intent.config.event

import com.leandro1995.seito.intent.callback.event.ExamAddIntentEventCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient
import com.leandro1995.seito.intent.event.ExamAddIntentEvent

class ExamAddIntentEventConfig(private val examAddIntentEventCallBack: ExamAddIntentEventCallBack?) :
    IntentConfigAmbient<ExamAddIntentEvent>() {

    override fun initConfig(event: ExamAddIntentEvent?) {
        when (event) {
            null -> {}
        }
    }
}