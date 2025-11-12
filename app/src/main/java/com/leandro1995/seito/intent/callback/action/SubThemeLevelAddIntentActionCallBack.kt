package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Level

interface SubThemeLevelAddIntentActionCallBack {

    fun starService()

    fun levelArrayList(levelArrayList: ArrayList<Level>, isShowButton: Boolean)
}