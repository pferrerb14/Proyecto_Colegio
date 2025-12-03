package com.leandro1995.seito.intent.action

import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Exam
import com.leandro1995.seito.model.entity.Student

class HomeStudentIntentAction(
    val student: Student? = null,
    val courseVideoArrayList: ArrayList<Course>? = null,
    val courseArrayList: ArrayList<Course>? = null,
    val examArrayList: ArrayList<Exam>? = null
)