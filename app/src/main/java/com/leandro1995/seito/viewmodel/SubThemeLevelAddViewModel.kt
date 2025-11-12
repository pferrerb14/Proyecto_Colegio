package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.SubThemeLevelAddIntentAction
import com.leandro1995.seito.intent.event.SubThemeLevelAddIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Level
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class SubThemeLevelAddViewModel :
    ViewModelAmbient<SubThemeLevelAddIntentAction, SubThemeLevelAddIntentEvent>() {

    var idCourse = ""
    var idTheme = ""
    var subTheme = SubTheme()
    var levelStringArrayList = arrayListOf<String>()

    private var teacher = Teacher()
    private val levelArrayList = arrayListOf<Level>()

    override fun event(action: Int) {
        when (action) {
            LEVEL -> {
                level()
            }

            LEVEL_ADD -> {
                levelAdd()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            LEVEL_FIREBASE -> {
                levelFirebase()
            }

            LEVEL_ADD_FIREBASE -> {
                levelAddFirebase()
            }
        }
    }

    private fun level() {
        loading(idService = LEVEL_FIREBASE)
    }

    private fun levelAdd() {
        loading(idService = LEVEL_ADD_FIREBASE)
    }

    private fun levelFirebase() {
        subTheme.levelFirebase(idCourse = idCourse, idTheme = idTheme, success = { response ->
            levelArrayList.clear()
            levelArrayList.addAll(response)
            value(
                action = SubThemeLevelAddIntentAction(
                    levelArrayList = levelArrayList, isShowButton = levelArrayList.isNotEmpty()
                )
            )
            loading()
        }, error = {
            levelArrayList.clear()
            value(action = SubThemeLevelAddIntentAction(levelArrayList = levelArrayList))
            loading()
        })
    }

    private fun levelAddFirebase() {
        teacher.addLevelFirebase(
            idCourse = idCourse,
            idTheme = idTheme,
            idSubTheme = subTheme.id,
            levelStringArrayList = levelStringArrayList,
            success = {
                loading(idService = LEVEL_FIREBASE, isDelayDisable = false)
            },
            error = {
                emit(
                    event = SubThemeLevelAddIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.not_name_theme_firebase_message
                        )
                    )
                )
                loading()
            })
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

    companion object {
        const val LEVEL = 0
        const val LEVEL_ADD = 1
        private const val LEVEL_FIREBASE = 2
        private const val LEVEL_ADD_FIREBASE = 3
    }
}