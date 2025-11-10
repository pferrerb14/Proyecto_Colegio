package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.CourseListIntentAction
import com.leandro1995.seito.intent.event.CourseListIntentEvent
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class CourseListViewModel : ViewModelAmbient<CourseListIntentAction, CourseListIntentEvent>()