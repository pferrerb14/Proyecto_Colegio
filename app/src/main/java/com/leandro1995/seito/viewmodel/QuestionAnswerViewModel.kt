package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.intent.action.QuestionAnswerIntentAction
import com.leandro1995.seito.intent.event.QuestionAnswerIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Option
import com.leandro1995.seito.model.entity.Question
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class QuestionAnswerViewModel :
    ViewModelAmbient<QuestionAnswerIntentAction, QuestionAnswerIntentEvent>() {

    var questionArrayList = arrayListOf<Question>()
    var timeSkip = ""
    val student = Student()
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

    override suspend fun service(idService: Int) {
        when (idService) {
            QUESTION_ANSWER_REGISTER_FIREBASE -> {
                questionAnswerRegisterFirebase()
            }

            UPDATE_COIN_FIREBASE -> {
                updateCoinFirebase()
            }
        }
    }

    fun coin() {
        if (student.coins >= Setting.DISCOUNT_CURRENCY) {
            student.coins = student.coins - Setting.DISCOUNT_CURRENCY
            questionArrayList[position].optionArrayList.find { it.name == questionArrayList[position].answer }
                ?.let {
                    it.isAnswer = true
                    it.isCoin = true
                }
            value(action = QuestionAnswerIntentAction(coin = student.coins))
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
                questionArrayList = questionArrayList, coin = student.coins
            )
        )
    }

    private fun page() {
        if ((questionArrayList.size - 1) != position) {
            position = position + 1
            value(action = QuestionAnswerIntentAction(isEnableNextButton = false))
            value(action = QuestionAnswerIntentAction(position = position))
        } else {
            loading(idService = QUESTION_ANSWER_REGISTER_FIREBASE)
        }
    }

    fun questionAnswerRegisterFirebase() {
        student.addAnswerFirebase(
            questionArrayList = questionArrayList,
            timeSkip = timeSkip,
            success = {
                loading(idService = UPDATE_COIN_FIREBASE, isDelayDisable = false)
            },
            error = {
                emit(
                    event = QuestionAnswerIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.no_question_register_message
                        )
                    )
                )
                loading()
            })
    }

    private fun updateCoinFirebase() {
        student.updateCoinFirebase(questionArrayList = questionArrayList, success = { result ->
            emit(
                event = QuestionAnswerIntentEvent.CompleteQuestionMessage(
                    alertMessage = AlertMessage(
                        idMessage = R.string.complete_register_message, isCancelable = false
                    ), updateCoin = result
                )
            )
            loading()
        }, error = {
            emit(
                event = QuestionAnswerIntentEvent.AlertMessage(
                    alertMessage = AlertMessage(
                        idMessage = R.string.no_question_register_message
                    )
                )
            )
            loading()
        })
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = QuestionAnswerIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    loading = Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    companion object {
        const val START_VIEW = 0
        const val PAGE = 1
        const val COIN = 2
        private const val QUESTION_ANSWER_REGISTER_FIREBASE = 3
        private const val UPDATE_COIN_FIREBASE = 4
    }
}