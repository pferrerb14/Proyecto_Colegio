package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.SubThemeAddIntentAction
import com.leandro1995.seito.intent.event.SubThemeAddIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class SubThemeAddViewModel : ViewModelAmbient<SubThemeAddIntentAction, SubThemeAddIntentEvent>() {

    var idCourse: String = ""
    var theme = Theme()

    override fun event(action: Int) {

    }

    override suspend fun service(idService: Int) {

    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = SubThemeAddIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }
}