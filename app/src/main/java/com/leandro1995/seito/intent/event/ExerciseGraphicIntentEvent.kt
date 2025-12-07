package com.leandro1995.seito.intent.event

import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient

sealed class ExerciseGraphicIntentEvent {

    data class Loading(val loadingIntentEventAmbient: LoadingIntentEventAmbient) :
        ExerciseGraphicIntentEvent()

    data class AlertMessage(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        ExerciseGraphicIntentEvent()
}