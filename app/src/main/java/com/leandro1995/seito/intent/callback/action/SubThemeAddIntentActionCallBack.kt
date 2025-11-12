package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.SubTheme

interface SubThemeAddIntentActionCallBack {

    fun startService()
    fun subThemeList(subThemeArrayList: ArrayList<SubTheme>)
}