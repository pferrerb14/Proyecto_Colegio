package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.ExamAddIntentAction
import com.leandro1995.seito.intent.event.ExamAddIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ExamAddViewModel : ViewModelAmbient<ExamAddIntentAction, ExamAddIntentEvent>() {

    var course: Course = Course()
    var theme = Theme()

    private val teacher = Teacher()

    override fun event(action: Int) {
        when (action) {
            COURSE -> {
                course()
            }

            THEME -> {
                theme()
            }

            SUB_THEME -> {
                subTheme()
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

            SUB_THEME_FIREBASE -> {
                subThemeFirebase()
            }
        }
    }

    private fun course() {
        loading(idService = COURSE_FIREBASE)
    }

    private fun theme() {
        loading(idService = THEME_FIREBASE, isDelayDisable = false)
    }

    private fun subTheme() {
        loading(idService = SUB_THEME_FIREBASE, isDelayDisable = false)
    }

    private fun courseFirebase() {
        teacher.courseFirebaseArrayList(success = { result ->
            value(action = ExamAddIntentAction(courseArrayList = result))
            loading()
        }, error = {
            emit(
                event = ExamAddIntentEvent.AlertMessage(
                    alertMessage = AlertMessage(idMessage = R.string.not_error_service_firebase_message)
                )
            )
            loading()
        })
    }

    private fun themeFirebase() {
        course.themeFirebase(success = { result ->
            value(action = ExamAddIntentAction(themeArrayList = result))
        }, error = {
            emit(
                event = ExamAddIntentEvent.AlertMessage(
                    alertMessage = AlertMessage(idMessage = R.string.not_error_service_firebase_message)
                )
            )
            loading()
        })
    }

    private fun subThemeFirebase() {
        theme.subThemeFirebase(idCourse = course.id, success = { result ->
            value(action = ExamAddIntentAction(subThemeArrayList = result))
        }, error = {
            emit(
                event = ExamAddIntentEvent.AlertMessage(
                    alertMessage = AlertMessage(idMessage = R.string.not_error_service_firebase_message)
                )
            )
            loading()
        })
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = ExamAddIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    loading = Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    companion object {
        const val COURSE = 0
        const val THEME = 1
        const val SUB_THEME = 2
        private const val COURSE_FIREBASE = 3
        private const val THEME_FIREBASE = 4
        private const val SUB_THEME_FIREBASE = 5
    }
}