package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.SubThemeLevelAddIntentAction
import com.leandro1995.seito.intent.event.SubThemeLevelAddIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Level
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class SubThemeLevelAddViewModel :
    ViewModelAmbient<SubThemeLevelAddIntentAction, SubThemeLevelAddIntentEvent>() {

    var idCourse = ""
    var idTheme = ""
    var subTheme = SubTheme()

    private val levelArrayList = arrayListOf<Level>()

    override fun event(action: Int) {
        when (action) {
            LEVEL -> {
                level()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            LEVEL_FIREBASE -> {
                levelFirebase()
            }
        }
    }

    private fun level() {
        loading(idService = LEVEL_FIREBASE)
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
        private const val LEVEL_FIREBASE = 1
    }
}