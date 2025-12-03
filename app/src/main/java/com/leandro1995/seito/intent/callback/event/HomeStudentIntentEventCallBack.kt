package com.leandro1995.seito.intent.callback.event

import com.leandro1995.seito.intent.callback.ambient.LoadingIntentCallBackAmbient
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Question

interface HomeStudentIntentEventCallBack : LoadingIntentCallBackAmbient {

    fun videoDetail(courseArrayList: ArrayList<Course>)
    fun questionAnswer(questionArrayList: ArrayList<Question>)
}