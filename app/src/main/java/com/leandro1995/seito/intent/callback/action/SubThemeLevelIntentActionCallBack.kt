package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Level

interface SubThemeLevelIntentActionCallBack {

    fun startService()
    fun levelArrayList(levelArrayList: ArrayList<Level>)
}