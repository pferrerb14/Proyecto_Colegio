package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.SubTheme

interface SubThemeIntentActionCallBack {

    fun startService()
    fun subThemeArrayList(subThemeArrayList: ArrayList<SubTheme>)
}