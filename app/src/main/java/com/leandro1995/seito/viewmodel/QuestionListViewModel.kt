package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.QuestionListIntentAction
import com.leandro1995.seito.intent.event.QuestionListIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Level
import com.leandro1995.seito.model.entity.Question
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class QuestionListViewModel :
    ViewModelAmbient<QuestionListIntentAction, QuestionListIntentEvent>() {

    var level = Level()
    private val questionArrayList = arrayListOf<Question>()

    override fun event(action: Int) {
        when (action) {
            QUESTION_ADD -> {
                questionAdd()
            }

            QUESTION_LIST -> {
                questionList()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            QUESTION_LIST_FIREBASE -> {
                questionFirebase()
            }
        }
    }

    private fun questionAdd() {
        emit(event = QuestionListIntentEvent.QuestionAdd)
    }

    private fun questionList() {
        loading(idService = QUESTION_LIST_FIREBASE)
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
        private const val QUESTION_LIST_FIREBASE = 2
    }
}