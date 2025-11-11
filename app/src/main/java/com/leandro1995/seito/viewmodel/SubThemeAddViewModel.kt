package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.SubThemeAddIntentAction
import com.leandro1995.seito.intent.event.SubThemeAddIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class SubThemeAddViewModel : ViewModelAmbient<SubThemeAddIntentAction, SubThemeAddIntentEvent>() {

    var idCourse: String = ""
    var theme = Theme()

    private val subthemeArrayList = ArrayList<SubTheme>()

    override fun event(action: Int) {
        when (action) {
            SUB_THEME_LIST -> {
                subThemeList()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            SUB_THEME_LIST_FIREBASE -> {
                subThemeListFirebase()
            }
        }
    }

    private fun subThemeList() {
        loading(idService = SUB_THEME_LIST_FIREBASE)
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
        private const val SUB_THEME_LIST_FIREBASE = 0
    }
}