package com.leandro1995.seito.intent.event

import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient

sealed class CourseListIntentEvent {

    data class Loading(val loadingIntentEventAmbient: LoadingIntentEventAmbient) :
        CourseListIntentEvent()
}