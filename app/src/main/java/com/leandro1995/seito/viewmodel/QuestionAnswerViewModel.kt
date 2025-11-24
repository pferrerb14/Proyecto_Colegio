package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.intent.action.QuestionAnswerIntentAction
import com.leandro1995.seito.intent.event.QuestionAnswerIntentEvent
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Question
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class QuestionAnswerViewModel :
    ViewModelAmbient<QuestionAnswerIntentAction, QuestionAnswerIntentEvent>() {

    var questionArrayList = arrayListOf<Question>()
    var coin = 0
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
        value(
            action = QuestionAnswerIntentAction(
                questionArrayList = questionArrayList, coin = coin
            )
        )
    }

    private fun page() {
        if (questionArrayList.size != position) {
            position = position + 1
            value(action = QuestionAnswerIntentAction(position = position))
        }
    }

    fun coin() {
        if (coin >= Setting.DISCOUNT_CURRENCY) {
            coin = coin - Setting.DISCOUNT_CURRENCY
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

    companion object {
        const val START_VIEW = 0
        const val PAGE = 1
        const val COIN = 2
    }
}