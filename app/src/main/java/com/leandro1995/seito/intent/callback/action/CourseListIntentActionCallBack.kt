package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Course

interface CourseListIntentActionCallBack {

    fun startService()
    fun courseArrayList(courseArrayList: ArrayList<Course>)
}