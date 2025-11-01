package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Student

interface ProfileIntentActionCallBack {

    fun getProtoDataStore()
    fun studentView(student: Student)
}