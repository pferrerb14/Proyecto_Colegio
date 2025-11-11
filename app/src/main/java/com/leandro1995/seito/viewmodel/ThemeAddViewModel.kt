package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.ThemeAddIntentAction
import com.leandro1995.seito.intent.event.ThemeAddIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ThemeAddViewModel : ViewModelAmbient<ThemeAddIntentAction, ThemeAddIntentEvent>() {

    var course = Course()

    private val themeArrayList = ArrayList<Theme>()

    override fun event(action: Int) {
        when (action) {
            THEME_ADD -> {
                themeAdd()
            }

            TOPIC_EDITOR_BOTTOM_SHEET -> {
                topicEditorBottomSheet()
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

    private fun topicEditorBottomSheet() {
        emit(event = ThemeAddIntentEvent.TopicEditorBottomSheet)
    }

    private fun themeAddFirebase() {
        course.themeFirebase(success = { response ->
            themeArrayList.clear()
            themeArrayList.addAll(response)
            value(action = ThemeAddIntentAction(themArrayList = themeArrayList))
            loading()
        }, error = {
            themeArrayList.clear()
            value(action = ThemeAddIntentAction(themArrayList = themeArrayList))
            loading()
        })
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
        const val TOPIC_EDITOR_BOTTOM_SHEET = 2
        private const val THEME_ADD_FIREBASE = 3
    }
}