package com.leandro1995.seito.intent.event

import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Question

sealed class HomeStudentIntentEvent {
    data class Loading(val loadingIntentEventAmbient: LoadingIntentEventAmbient) :
        HomeStudentIntentEvent()

    data class VideoDetail(val courseArrayList: ArrayList<Course>) : HomeStudentIntentEvent()

    data class QuestionAnswer(val questionArrayList: ArrayList<Question>) : HomeStudentIntentEvent()
}