package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class SubThemeLevelViewModel : ViewModelAmbient<Any, Any>() {

    var idCourse = ""
    var idTheme = ""
    var subTheme = SubTheme()
}