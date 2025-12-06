package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Note

interface ExamGraphicIntentActionCallBack {

    fun startService()
    fun noteArrayList(noteArrayList: ArrayList<Note>)
}