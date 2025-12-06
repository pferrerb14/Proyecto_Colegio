package com.leandro1995.seito.intent.action

import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Note
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.model.entity.Theme

class ExerciseGraphicIntentAction(
    val courseArrayList: ArrayList<Course>? = null,
    val themeArrayList: ArrayList<Theme>? = null,
    val subThemeArrayList: ArrayList<SubTheme>? = null,
    val noteLeveOneArrayList: ArrayList<Note>? = null,
    val noteLeveTwoArrayList: ArrayList<Note>? = null
)