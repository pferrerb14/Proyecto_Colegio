package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.ExamListIntentAction
import com.leandro1995.seito.intent.event.ExamListIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ExamListViewModel : ViewModelAmbient<ExamListIntentAction, ExamListIntentEvent>() {

    private val teacher = Teacher()
    var idExam = ""

    override fun event(action: Int) {
        when (action) {
            EXAM_ADD -> {
                examAdd()
            }

            EXAM_LIST -> {
                examList()
            }

            EXAM_DELETE -> {
                examDelete()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            EXAM_FIREBASE -> {
                examFirebase()
            }

            EXAM_DELETE_FIREBASE -> {
                examDeleteFirebase()
            }
        }
    }

    private fun examAdd() {
        emit(event = ExamListIntentEvent.ExamAdd)
    }

    private fun examList() {
        loading(idService = EXAM_FIREBASE)
    }

    private fun examDelete() {
        loading(idService = EXAM_DELETE_FIREBASE)
    }

    private fun examFirebase() {
        teacher.examFirebaseArrayList(success = { result ->
            value(action = ExamListIntentAction(examArrayList = result))
            loading()
        }, error = {
            value(action = ExamListIntentAction(examArrayList = arrayListOf()))
            loading()
        })
    }

    private fun examDeleteFirebase() {
        teacher.deleteExamFirebase(idExam = idExam, success = {
            loading(idService = EXAM_FIREBASE, isDelayDisable = false)
        }, error = {
            emit(
                event = ExamListIntentEvent.AlertMessage(
                    alertMessage = AlertMessage(idMessage = R.string.not_error_service_firebase_message)
                )
            )
            loading()
        })
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = ExamListIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    loading = Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    companion object {
        const val EXAM_ADD = 0
        const val EXAM_LIST = 1
        const val EXAM_DELETE = 2
        private const val EXAM_FIREBASE = 3
        private const val EXAM_DELETE_FIREBASE = 4
    }
}