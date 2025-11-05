package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Student

interface HomeIntentActionCallBack {

    fun getProtoDataStore()
    fun studentDetail(student: Student)
    fun courseArrayList(courseArrayList: ArrayList<Course>)
}