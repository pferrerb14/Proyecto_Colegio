package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.SubThemeAddIntentAction
import com.leandro1995.seito.intent.event.SubThemeAddIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class SubThemeAddViewModel : ViewModelAmbient<SubThemeAddIntentAction, SubThemeAddIntentEvent>() {

    var idCourse: String = ""
    var themeName = ""
    var theme = Theme()

    private val subthemeArrayList = ArrayList<SubTheme>()
    private val teacher = Teacher()

    override fun event(action: Int) {
        when (action) {
            SUB_THEME_LIST -> {
                subThemeList()
            }

            TOPIC_EDITOR_BOTTOM_SHEET -> {
                topicEditorBottomSheet()
            }

            NAME_SUB_THEME_VALIDATION -> {
                nameSubThemeValidation()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            SUB_THEME_LIST_FIREBASE -> {
                subThemeListFirebase()
            }

            SUB_THEME_ADD_FIREBASE -> {
                subThemeAddFirebase()
            }
        }
    }

    private fun subThemeList() {
        loading(idService = SUB_THEME_LIST_FIREBASE)
    }

    private fun topicEditorBottomSheet() {
        emit(event = SubThemeAddIntentEvent.TopicEditorBottomSheet)
    }

    fun nameSubThemeValidation() {
        if (themeName.isEmpty()) {
            emit(event = SubThemeAddIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.not_name_theme_firebase_message)))
        } else {
            loading(idService = SUB_THEME_ADD_FIREBASE)
        }
    }

    private fun subThemeAddFirebase() {
        teacher.addSubThemeFirebase(
            idCourse = idCourse,
            themeName = themeName,
            idTheme = theme.id,
            success = {
                loading(idService = SUB_THEME_LIST_FIREBASE, isDelayDisable = false)
            },
            error = {
                emit(
                    event = SubThemeAddIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.not_name_theme_firebase_message
                        )
                    )
                )
                loading()
            })
    }

    private fun subThemeListFirebase() {
        theme.subThemeFirebase(idCourse = idCourse, success = { response ->
            subthemeArrayList.clear()
            subthemeArrayList.addAll(response)
            value(action = SubThemeAddIntentAction(subThemeArrayList = subthemeArrayList))
            loading()
        }, error = {
            subthemeArrayList.clear()
            loading()
        })
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

    companion object {
        const val SUB_THEME_LIST = 0
        const val TOPIC_EDITOR_BOTTOM_SHEET = 1
        const val NAME_SUB_THEME_VALIDATION = 2
        private const val SUB_THEME_LIST_FIREBASE = 3
        private const val SUB_THEME_ADD_FIREBASE = 4
    }
}