package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.SubThemeLevelIntentAction
import com.leandro1995.seito.intent.event.SubThemeLevelIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Level
import com.leandro1995.seito.model.entity.Question
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class SubThemeLevelViewModel :
    ViewModelAmbient<SubThemeLevelIntentAction, SubThemeLevelIntentEvent>() {

    var idCourse = ""
    var idTheme = ""
    var subTheme = SubTheme()
    var level = Level()

    private val levelArrayList = arrayListOf<Level>()
    private val questionArrayList = arrayListOf<Question>()

    override fun event(action: Int) {
        when (action) {
            LEVEL -> {
                level()
            }

            ANSWER_QUESTION -> {
                answerQuestion()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            LEVEL_FIREBASE -> {
                levelFirebase()
            }

            ANSWER_QUESTION_FIREBASE -> {
                answerQuestionFirebase()
            }
        }
    }

    private fun level() {
        loading(idService = LEVEL_FIREBASE)
    }

    private fun levelFirebase() {
        subTheme.levelFirebase(idCourse = idCourse, idTheme = idTheme, success = { result ->
            levelArrayList.clear()
            levelArrayList.addAll(result)
            value(action = SubThemeLevelIntentAction(levelArrayList = levelArrayList))
            loading()
        }, error = {
            levelArrayList.clear()
            value(action = SubThemeLevelIntentAction(levelArrayList = levelArrayList))
            loading()
        })
    }

    private fun answerQuestion() {
        loading(idService = ANSWER_QUESTION_FIREBASE)
    }

    private fun answerQuestionFirebase() {
        level.questionFirebase(success = { result ->
            questionArrayList.clear()
            questionArrayList.addAll(result)

            if (questionArrayList.isEmpty()) {
                emit(
                    event = SubThemeLevelIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.no_question_message
                        )
                    )
                )
            } else {
                
            }
            loading()
        }, error = {
            emit(
                event = SubThemeLevelIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.no_question_message))
            )
            loading()
        })
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = SubThemeLevelIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    companion object {
        const val LEVEL = 0
        const val ANSWER_QUESTION = 1
        private const val LEVEL_FIREBASE = 2
        private const val ANSWER_QUESTION_FIREBASE = 3
    }
}