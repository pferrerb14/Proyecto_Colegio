package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.ThemeIntentAction
import com.leandro1995.seito.intent.event.ThemeIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ThemeViewModel : ViewModelAmbient<ThemeIntentAction, ThemeIntentEvent>() {

    var course = Course()

    private val themeArrayList = arrayListOf<Theme>()

    override fun event(action: Int) {
        when (action) {
            THEME -> {
                theme()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            THEME_FIREBASE -> {
                themeFirebase()
            }
        }
    }

    private fun theme() {
        loading(idService = THEME_FIREBASE)
    }

    private fun themeFirebase() {
        course.themeFirebase(success = { response ->
            themeArrayList.clear()
            themeArrayList.addAll(response)
            value(action = ThemeIntentAction(themeArrayList = themeArrayList))
            loading()
        }, error = {
            themeArrayList.clear()
            value(action = ThemeIntentAction(themeArrayList = themeArrayList))
            loading()
        })
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