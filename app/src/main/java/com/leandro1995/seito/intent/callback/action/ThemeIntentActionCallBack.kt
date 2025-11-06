package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Theme

interface ThemeIntentActionCallBack {

    fun startService()
    fun themeArrayList(themeArrayList: ArrayList<Theme>)
}