package com.leandro1995.seito.intent.action

import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Student

class HomeIntentAction(val student: Student? = null, val courseVideoArrayList: ArrayList<Course>? = null)