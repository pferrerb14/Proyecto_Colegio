package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.QuestionListIntentAction
import com.leandro1995.seito.intent.event.QuestionListIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Level
import com.leandro1995.seito.model.entity.Question
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class QuestionListViewModel :
    ViewModelAmbient<QuestionListIntentAction, QuestionListIntentEvent>() {

    var level = Level()
    var question = Question()
    private val questionArrayList = arrayListOf<Question>()

    override fun event(action: Int) {
        when (action) {
            QUESTION_ADD -> {
                questionAdd()
            }

            QUESTION_LIST -> {
                questionList()
            }

            QUESTION_DELETE -> {
                questionDelete()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            QUESTION_LIST_FIREBASE -> {
                questionFirebase()
            }

            QUESTION_DELETE_FIREBASE -> {
                questionDeleteFirebase()
            }
        }
    }

    private fun questionAdd() {
        emit(event = QuestionListIntentEvent.QuestionAdd)
    }

    private fun questionList() {
        loading(idService = QUESTION_LIST_FIREBASE)
    }

    private fun questionDelete() {
        loading(idService = QUESTION_DELETE_FIREBASE)
    }

    private fun questionFirebase() {
        level.questionFirebase(success = { response ->
            questionArrayList.clear()
            questionArrayList.addAll(response)
            value(action = QuestionListIntentAction(questionArrayList = questionArrayList))
            loading()
        }, error = {
            questionArrayList.clear()
            value(action = QuestionListIntentAction(questionArrayList = questionArrayList))
            loading()
        })
    }

    private fun questionDeleteFirebase() {
        question.deleteFirebase(success = {
            loading(idService = QUESTION_LIST_FIREBASE, isDelayDisable = false)
        }, error = {
            emit(
                event = QuestionListIntentEvent.AlertMessage(
                    alertMessage = AlertMessage(
                        idMessage = R.string.no_register_student_message
                    )
                )
            )
            loading()
        })
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = QuestionListIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    loading = Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    companion object {
        const val QUESTION_ADD = 0
        const val QUESTION_LIST = 1
        const val QUESTION_DELETE = 2
        private const val QUESTION_LIST_FIREBASE = 3
        private const val QUESTION_DELETE_FIREBASE = 4
    }
}