package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.ThemeAddIntentAction
import com.leandro1995.seito.intent.event.ThemeAddIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ThemeAddViewModel : ViewModelAmbient<ThemeAddIntentAction, ThemeAddIntentEvent>() {

    var course = Course()

    override fun event(action: Int) {
        when (action) {
            THEME_ADD -> {
                themeAdd()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            THEME_ADD_FIREBASE -> {
                themeAddFirebase()
            }
        }
    }

    private fun themeAdd() {
        loading(idService = THEME_ADD_FIREBASE)
    }

    private fun themeAddFirebase() {

    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = ThemeAddIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    companion object {
        const val THEME_ADD = 1
        private const val THEME_ADD_FIREBASE = 2
    }
}