package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.intent.action.ExamAddIntentAction
import com.leandro1995.seito.intent.event.ExamAddIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Exam
import com.leandro1995.seito.model.entity.Level
import com.leandro1995.seito.model.entity.Question
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ExamAddViewModel : ViewModelAmbient<ExamAddIntentAction, ExamAddIntentEvent>() {

    var theme = Theme()
    var exam = Exam()
    var questionIdArrayList = arrayListOf<String>()

    private var course: Course = Course()
    private val teacher = Teacher()
    private var subTheme = SubTheme()
    private val levelArrayList = arrayListOf<Level>()
    private val questionArrayList = arrayListOf<Question>()

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

            LEVEL -> {
                level()
            }

            ACTIVATE_BUTTON -> {
                activateButton()
            }

            EXM_VALIDATION -> {
                examValidation()
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

            LEVEL_FIREBASE -> {
                levelFirebase()
            }

            QUESTION_FIREBASE -> {
                questionFirebase()
            }

            EXM_FIREBASE -> {
                examFirebase()
            }
        }
    }

    fun courseSelect(course: Course) {
        if (!course.isIdEmpty()) {
            this.course = course
            button.invoke(THEME)
        } else {
            value(action = ExamAddIntentAction(themeArrayList = arrayListOf()))
        }
    }

    fun themeSelect(theme: Theme) {
        if (!theme.isIdEmpty()) {
            this.theme = theme
            button.invoke(SUB_THEME)
        } else {
            value(action = ExamAddIntentAction(subThemeArrayList = arrayListOf()))
        }
    }

    fun subThemeSelect(subTheme: SubTheme) {
        if (!subTheme.isIdEmpty()) {
            this.subTheme = subTheme
            button.invoke(LEVEL)
        } else {
            value(action = ExamAddIntentAction(questionArrayList = arrayListOf()))
        }
    }

    fun questionIdArrayList(questionIdArrayList: ArrayList<String>) {
        this.questionIdArrayList.clear()
        this.questionIdArrayList.addAll(questionIdArrayList)

        button.invoke(ACTIVATE_BUTTON)
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

    private fun level() {
        loading(idService = LEVEL_FIREBASE, isDelayDisable = false)
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

    private fun subThemeFirebase() {
        theme.subThemeFirebase(idCourse = course.id, success = { result ->
            value(action = ExamAddIntentAction(subThemeArrayList = result))
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

    private fun levelFirebase() {
        subTheme.levelFirebase(idCourse = course.id, idTheme = theme.id, success = { result ->
            levelArrayList.clear()
            levelArrayList.addAll(result)
            questionArrayList.clear()
            if (levelArrayList.isEmpty()) {
                value(action = ExamAddIntentAction(questionArrayList = arrayListOf()))
                loading()
            } else {
                loading(idService = QUESTION_FIREBASE, isDelayDisable = false)
            }
        }, error = {
            emit(
                event = ExamAddIntentEvent.AlertMessage(
                    alertMessage = AlertMessage(idMessage = R.string.not_error_service_firebase_message)
                )
            )
            loading()
        })
    }

    private fun questionFirebase(position: Int = 0) {
        levelArrayList.getOrNull(position)?.let {
            it.questionFirebase(success = { result ->
                questionArrayList.addAll(result)
                questionFirebase(position + 1)
            }, error = {
                value(action = ExamAddIntentAction(questionArrayList = questionArrayList))
                loading()
            })
        } ?: {
            value(action = ExamAddIntentAction(questionArrayList = questionArrayList))
            loading()
        }
    }

    private fun examFirebase() {
        teacher.addExamFirebase(
            exam = exam,
            questionArrayList = ArrayList(questionArrayList.filter { it.id in questionIdArrayList }),
            success = {
                emit(
                    event = ExamAddIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(idMessage = R.string.complete_exam_message)
                    )
                )
                loading()
            },
            error = {
                emit(
                    event = ExamAddIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(idMessage = R.string.not_error_service_firebase_message)
                    )
                )
                loading()
            })
    }

    private fun activateButton() {
        value(action = ExamAddIntentAction(activateButton = questionIdArrayList.size == Setting.QUESTION_SELECT_MAX))
    }

    private fun examValidation() {
        when {
            exam.isNameEmpty() -> {
                emit(
                    event = ExamAddIntentEvent.RegisterAlertMessage(
                        alertMessage = AlertMessage(idMessage = R.string.not_name_exam_message)
                    )
                )
            }

            else -> {
                loading(idService = EXM_FIREBASE)
            }
        }
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
        private const val THEME = 1
        const val SUB_THEME = 2
        const val EXM_VALIDATION = 3
        private const val LEVEL = 4
        private const val COURSE_FIREBASE = 5
        private const val THEME_FIREBASE = 6
        private const val SUB_THEME_FIREBASE = 7
        private const val LEVEL_FIREBASE = 8
        private const val QUESTION_FIREBASE = 9
        private const val ACTIVATE_BUTTON = 10
        private const val EXM_FIREBASE = 11
    }
}