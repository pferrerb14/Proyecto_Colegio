package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.model.entity.Teacher

interface ProfileIntentActionCallBack {

    fun getProtoDataStore()
    fun studentView(student: Student)
    fun teacherView(teacher: Teacher)
}