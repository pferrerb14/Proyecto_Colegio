package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.intent.action.QuestionAnswerIntentAction
import com.leandro1995.seito.intent.event.QuestionAnswerIntentEvent
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Option
import com.leandro1995.seito.model.entity.Question
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class QuestionAnswerViewModel :
    ViewModelAmbient<QuestionAnswerIntentAction, QuestionAnswerIntentEvent>() {

    var questionArrayList = arrayListOf<Question>()
    var coin = 0
    private var position = 0
    private val student = Student()

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

    fun coin() {
        if (coin >= Setting.DISCOUNT_CURRENCY) {
            coin = coin - Setting.DISCOUNT_CURRENCY
            questionArrayList[position].optionArrayList.find { it.name == questionArrayList[position].answer }?.isAnswer =
                true
            value(action = QuestionAnswerIntentAction(coin = coin))
            button.invoke(PAGE)
        } else {
            emit(
                event = QuestionAnswerIntentEvent.AlertMessage(
                    alertMessage = AlertMessage(
                        idMessage = R.string.no_coin_message
                    )
                )
            )
        }
    }

    fun option(option: Option) {
        questionArrayList[position].optionArrayList.apply {
            forEach { it.isAnswer = false }
            find { it.name == option.name }?.isAnswer = true
        }

        value(action = QuestionAnswerIntentAction(isEnableNextButton = true))
    }

    private fun startView() {
        value(
            action = QuestionAnswerIntentAction(
                questionArrayList = questionArrayList, coin = coin
            )
        )
    }

    private fun page() {
        if ((questionArrayList.size - 1) != position) {
            position = position + 1
            value(action = QuestionAnswerIntentAction(isEnableNextButton = false))
            value(action = QuestionAnswerIntentAction(position = position))
        } else {
            student.addAnswerFirebase(questionArrayList = questionArrayList)
        }
    }

    companion object {
        const val START_VIEW = 0
        const val PAGE = 1
        const val COIN = 2
    }
}