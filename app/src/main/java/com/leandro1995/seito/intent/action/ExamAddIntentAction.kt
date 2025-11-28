package com.leandro1995.seito.intent.action

import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Question
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.model.entity.Theme

class ExamAddIntentAction(
    val courseArrayList: ArrayList<Course>? = null,
    val themeArrayList: ArrayList<Theme>? = null,
    val subThemeArrayList: ArrayList<SubTheme>? = null,
    val questionArrayList: ArrayList<Question>? = null
)