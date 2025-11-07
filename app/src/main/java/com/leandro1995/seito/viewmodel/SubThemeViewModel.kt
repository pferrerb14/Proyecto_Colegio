package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.SubThemeIntentAction
import com.leandro1995.seito.intent.event.SubThemeIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class SubThemeViewModel : ViewModelAmbient<SubThemeIntentAction, SubThemeIntentEvent>() {

    var theme = Theme()

    override fun event(action: Int) {
        when (action) {

        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {

        }
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = SubThemeIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }
}