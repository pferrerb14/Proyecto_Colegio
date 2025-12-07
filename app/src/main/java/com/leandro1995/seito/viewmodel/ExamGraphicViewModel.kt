package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.fcm.firestore.config.Setting
import com.leandro1995.seito.intent.action.ExamGraphicIntentAction
import com.leandro1995.seito.intent.event.ExamGraphicIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Note
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ExamGraphicViewModel : ViewModelAmbient<ExamGraphicIntentAction, ExamGraphicIntentEvent>() {

    val student = Student()
    private var note = Note()

    override fun event(action: Int) {
        when (action) {
            EXAM -> {
                exam()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            EXAM_FIREBASE -> {
                examFirebase()
            }

            QUESTION_FIREBASE -> {
                questionFirebase()
            }
        }
    }

    fun noteSelect(note: Note) {
        this.note = note
        loading(idService = QUESTION_FIREBASE, false)
        //emit(event = ExamGraphicIntentEvent.NoteDetail(note = note))
    }

    private fun exam() {
        loading(idService = EXAM_FIREBASE)
    }

    private fun examFirebase() {
        student.noteFirebaseArrayList(idCollection = Setting.EXAM, success = { result ->
            value(action = ExamGraphicIntentAction(noteArrayList = result))
            loading()
        }, error = {
            emit(
                event = ExamGraphicIntentEvent.AlertMessage(
                    alertMessage = AlertMessage(idMessage = R.string.not_error_service_firebase_message)
                )
            )
            loading()
        })
    }

    private fun questionFirebase() {
        note.questionFirebaseArrayList(success = { result ->
            emit(event = ExamGraphicIntentEvent.NoteDetail(note = note))
            loading()
        }, error = {
            emit(
                event = ExamGraphicIntentEvent.AlertMessage(
                    alertMessage = AlertMessage(idMessage = R.string.not_error_service_firebase_message)
                )
            )
            loading()
        })
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = ExamGraphicIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    loading = Loading(
                        idService = idService, isDelayDisable = isDelayDisable
                    )
                )
            )
        )
    }

    companion object {
        const val EXAM = 0
        private const val EXAM_FIREBASE = 1
        private const val QUESTION_FIREBASE = 2
    }
}