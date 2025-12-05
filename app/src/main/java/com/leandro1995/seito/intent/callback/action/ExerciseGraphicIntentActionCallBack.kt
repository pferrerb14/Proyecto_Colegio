package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.model.entity.Theme

interface ExerciseGraphicIntentActionCallBack {

    fun startService()
    fun courseArrayList(courseArrayList: ArrayList<Course>)
    fun themeArrayList(themeArrayList: ArrayList<Theme>)
    fun subThemeArrayList(subThemeArrayList: ArrayList<SubTheme>)
}