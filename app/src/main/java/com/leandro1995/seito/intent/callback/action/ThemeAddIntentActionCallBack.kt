package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Theme

interface ThemeAddIntentActionCallBack {

    fun startService()
    fun themeArrayList(themeArrayList: ArrayList<Theme>)
}