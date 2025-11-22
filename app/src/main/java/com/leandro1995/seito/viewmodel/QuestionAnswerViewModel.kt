package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.QuestionAnswerIntentAction
import com.leandro1995.seito.intent.event.QuestionAnswerIntentEvent
import com.leandro1995.seito.model.entity.Question
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class QuestionAnswerViewModel :
    ViewModelAmbient<QuestionAnswerIntentAction, QuestionAnswerIntentEvent>() {

    var questionArrayList = arrayListOf<Question>()

    override fun event(action: Int) {
        when (action) {
            START_VIEW -> {
                startView()
            }
        }
    }

    private fun startView() {
        value(action = QuestionAnswerIntentAction(questionArrayList = questionArrayList))
    }

    companion object {
        const val START_VIEW = 0
    }
}