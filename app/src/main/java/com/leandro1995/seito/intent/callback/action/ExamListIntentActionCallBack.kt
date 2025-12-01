package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Exam

interface ExamListIntentActionCallBack {

    fun startService()
    fun examArrayList(examArrayList: ArrayList<Exam>)
}