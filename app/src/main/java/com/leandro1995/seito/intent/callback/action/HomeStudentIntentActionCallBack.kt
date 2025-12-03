package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Exam
import com.leandro1995.seito.model.entity.Student

interface HomeStudentIntentActionCallBack {

    fun getProtoDataStore()
    fun studentDetail(student: Student)
    fun courseVideoArrayList(courseArrayList: ArrayList<Course>)
    fun courseArrayList(courseArrayList: ArrayList<Course>)
    fun examArrayList(examArrayList: ArrayList<Exam>)
}