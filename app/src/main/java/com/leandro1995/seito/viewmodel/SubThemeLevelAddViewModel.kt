package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.SubThemeLevelAddIntentAction
import com.leandro1995.seito.intent.event.SubThemeLevelAddIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class SubThemeLevelAddViewModel :
    ViewModelAmbient<SubThemeLevelAddIntentAction, SubThemeLevelAddIntentEvent>() {

    var idCourse = ""
    var idTheme = ""
    var subTheme = SubTheme()

    override fun event(action: Int) {

    }

    override suspend fun service(idService: Int) {
        
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = SubThemeLevelAddIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }
}