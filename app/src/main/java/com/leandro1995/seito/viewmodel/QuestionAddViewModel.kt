package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.intent.action.QuestionAddIntentAction
import com.leandro1995.seito.intent.event.QuestionAddIntentEvent
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Question
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class QuestionAddViewModel : ViewModelAmbient<QuestionAddIntentAction, QuestionAddIntentEvent>() {

    val question = Question()

    override fun event(action: Int) {
        when (action) {
            QUESTION_VALIDATION -> {
                questionValidation()
            }

            OPTION_ADD_BOTTOM_SHEET -> {
                optionAddBottomSheet()
            }
        }
    }

    private fun questionValidation() {
        when {
            question.isName() -> {
                emit(
                    event = QuestionAddIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.not_question_title_message
                        )
                    )
                )
            }

            question.isCoin() -> {
                emit(
                    event = QuestionAddIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.not_coin_message
                        )
                    )
                )
            }

            question.isOptionArrayList() -> {
                emit(
                    event = QuestionAddIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.not_option_message
                        )
                    )
                )
            }

            question.optionLength() -> {
                emit(
                    event = QuestionAddIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.not_option_length_message
                        )
                    )
                )
            }
        }
    }

    private fun optionAddBottomSheet() {
        emit(
            event = QuestionAddIntentEvent.OptionAddBottomSheet(
                isAnswer = question.isAnswerSelect()
            )
        )
    }

    companion object {
        const val QUESTION_VALIDATION = 0
        const val OPTION_ADD_BOTTOM_SHEET = 1
    }
}