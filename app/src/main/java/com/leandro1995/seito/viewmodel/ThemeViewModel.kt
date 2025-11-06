package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.ThemeIntentAction
import com.leandro1995.seito.intent.event.ThemeIntentEvent
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ThemeViewModel : ViewModelAmbient<ThemeIntentAction, ThemeIntentEvent>() {

    var course = Course()
}