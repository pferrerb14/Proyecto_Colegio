package com.leandro1995.seito.intent.callback.event

import com.leandro1995.seito.intent.callback.ambient.LoadingIntentCallBackAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Note

interface ExerciseGraphicIntentEventCallBack : LoadingIntentCallBackAmbient {

    fun alertMessage(alertMessage: AlertMessage)
    fun noteDetail(note: Note)
}