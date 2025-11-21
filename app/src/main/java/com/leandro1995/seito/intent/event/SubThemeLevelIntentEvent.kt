package com.leandro1995.seito.intent.event

import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Question

sealed class SubThemeLevelIntentEvent {
    data class Loading(val loadingIntentEventAmbient: LoadingIntentEventAmbient) :
        SubThemeLevelIntentEvent()

    data class AlertMessage(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        SubThemeLevelIntentEvent()

    data class QuestionAnswer(val questionArrayList: ArrayList<Question>) :
        SubThemeLevelIntentEvent()
}