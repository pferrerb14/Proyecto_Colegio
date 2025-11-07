package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class SubThemeViewModel : ViewModelAmbient<Any, Any>() {

    var theme = Theme()
}