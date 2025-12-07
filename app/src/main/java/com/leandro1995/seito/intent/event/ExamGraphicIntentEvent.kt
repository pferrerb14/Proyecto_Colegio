package com.leandro1995.seito.intent.event

import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Note
import com.leandro1995.seito.model.entity.Question

sealed class ExamGraphicIntentEvent {
    data class Loading(val loadingIntentEventAmbient: LoadingIntentEventAmbient) :
        ExamGraphicIntentEvent()

    data class AlertMessage(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        ExamGraphicIntentEvent()

    data class NoteDetail(val note: Note, val questionArrayList: ArrayList<Question>) :
        ExamGraphicIntentEvent()
}