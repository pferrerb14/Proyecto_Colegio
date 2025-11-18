package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.QuestionListIntentAction
import com.leandro1995.seito.intent.event.QuestionListIntentEvent
import com.leandro1995.seito.model.entity.Level
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class QuestionListViewModel :
    ViewModelAmbient<QuestionListIntentAction, QuestionListIntentEvent>() {

    var level = Level()

    override fun event(action: Int) {
        when (action) {
            QUESTION_ADD -> {
                questionAdd()
            }
        }
    }

    private fun questionAdd() {
        emit(event = QuestionListIntentEvent.QuestionAdd)
    }

    companion object {
        const val QUESTION_ADD = 0
    }
}