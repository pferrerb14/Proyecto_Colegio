package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.ThemeIntentAction
import com.leandro1995.seito.intent.event.ThemeIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ThemeViewModel : ViewModelAmbient<ThemeIntentAction, ThemeIntentEvent>() {

    var course = Course()

    override fun event(action: Int) {
        when (action) {
            THEME -> {
                theme()
            }
        }
    }

    private fun theme() {
        loading(idService = THEME_FIREBASE)
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = ThemeIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    companion object {
        const val THEME = 0
        private const val THEME_FIREBASE = 1
    }
}