package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Student

interface StudentListIntentActionCallBack {

    fun startService()
    fun studentArrayList(studentArrayList: ArrayList<Student>)
}