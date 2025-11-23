package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.QuestionAnswerIntentAction
import com.leandro1995.seito.intent.event.QuestionAnswerIntentEvent
import com.leandro1995.seito.model.entity.Question
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class QuestionAnswerViewModel :
    ViewModelAmbient<QuestionAnswerIntentAction, QuestionAnswerIntentEvent>() {

    var questionArrayList = arrayListOf<Question>()
    private var position = 0

    override fun event(action: Int) {
        when (action) {
            START_VIEW -> {
                startView()
            }

            PAGE -> {
                page()
            }

            COIN -> {
                coin()
            }
        }
    }

    private fun startView() {
        value(action = QuestionAnswerIntentAction(questionArrayList = questionArrayList))
    }

    private fun page() {
        if (questionArrayList.size != position) {
            position = position + 1
            value(action = QuestionAnswerIntentAction(position = position))
        }
    }

    fun coin() {
        button.invoke(PAGE)
    }

    companion object {
        const val START_VIEW = 0
        const val PAGE = 1
        const val COIN = 2
    }
}