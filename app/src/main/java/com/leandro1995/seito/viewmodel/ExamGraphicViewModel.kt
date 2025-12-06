package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.ExamGraphicIntentAction
import com.leandro1995.seito.intent.event.ExamGraphicIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ExamGraphicViewModel : ViewModelAmbient<ExamGraphicIntentAction, ExamGraphicIntentEvent>() {

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = ExamGraphicIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    loading = Loading(
                        idService = idService, isDelayDisable = isDelayDisable
                    )
                )
            )
        )
    }
}