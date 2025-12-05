package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.ExerciseGraphicIntentAction
import com.leandro1995.seito.intent.event.ExerciseGraphicIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ExerciseGraphicViewModel :
    ViewModelAmbient<ExerciseGraphicIntentAction, ExerciseGraphicIntentEvent>() {

    private val teacher = Teacher()
    private var course: Course = Course()

    override fun event(action: Int) {
        when (action) {
            COURSE -> {
                course()
            }

            THEME -> {
                theme()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            COURSE_FIREBASE -> {
                courseFirebase()
            }

            THEME_FIREBASE -> {
                themeFirebase()
            }
        }
    }

    fun courseSelect(course: Course) {
        if (!course.isIdEmpty()) {
            this.course = course
            button.invoke(THEME)
        } else {
            value(action = ExerciseGraphicIntentAction(themeArrayList = arrayListOf()))
        }
    }

    private fun course() {
        loading(idService = COURSE_FIREBASE)
    }

    private fun theme() {
        loading(idService = THEME_FIREBASE, isDelayDisable = false)
    }

    private fun courseFirebase() {
        teacher.courseFirebaseArrayList(success = { result ->
            value(action = ExerciseGraphicIntentAction(courseArrayList = result))
            loading()
        }, error = {
            emit(
                event = ExerciseGraphicIntentEvent.AlertMessage(
                    alertMessage = AlertMessage(idMessage = R.string.not_error_service_firebase_message)
                )
            )
            loading()
        })
    }

    private fun themeFirebase() {
        course.themeFirebase(success = { result ->
            value(action = ExerciseGraphicIntentAction(themeArrayList = result))
            loading()
        }, error = {
            emit(
                event = ExerciseGraphicIntentEvent.AlertMessage(
                    alertMessage = AlertMessage(idMessage = R.string.not_error_service_firebase_message)
                )
            )
            loading()
        })
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = ExerciseGraphicIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    loading = Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    companion object {
        const val COURSE = 0
        private const val THEME = 1
        private const val COURSE_FIREBASE = 2
        private const val THEME_FIREBASE = 3
    }
}