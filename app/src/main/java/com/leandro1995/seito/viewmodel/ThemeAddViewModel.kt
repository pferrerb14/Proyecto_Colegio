package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ThemeAddViewModel : ViewModelAmbient<Any, Any>() {

    var course = Course()
}