package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.ExerciseGraphicIntentAction
import com.leandro1995.seito.intent.event.ExerciseGraphicIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Level
import com.leandro1995.seito.model.entity.Note
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ExerciseGraphicViewModel :
    ViewModelAmbient<ExerciseGraphicIntentAction, ExerciseGraphicIntentEvent>() {

    var student = Student()
    private val teacher = Teacher()
    private var course: Course = Course()
    private var theme = Theme()
    private var subTheme = SubTheme()
    private var levelArrayList = arrayListOf<Level>()
    private var note = Note()

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

            EXERCISE_FIREBASE -> {
                exerciseFirebase()
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

    fun themeSelect(theme: Theme) {
        if (!theme.isIdEmpty()) {
            this.theme = theme
            button.invoke(SUB_THEME)
        } else {
            value(action = ExerciseGraphicIntentAction(subThemeArrayList = arrayListOf()))
        }
    }

    fun subThemeSelect(subTheme: SubTheme) {
        if (!subTheme.isIdEmpty()) {
            this.subTheme = subTheme
            button.invoke(LEVEL)
        } else {
            value(
                action = ExerciseGraphicIntentAction(
                    noteLeveOneArrayList = arrayListOf(),
                    noteLeveTwoArrayList = arrayListOf(),
                    noteArrayList = arrayListOf()
                )
            )
        }
    }

    fun noteSelect(note: Note) {
        this.note = note
        emit(event = ExerciseGraphicIntentEvent.NoteDetail(note = note))
    }

    private fun subTheme() {
        loading(idService = SUB_THEME_FIREBASE, isDelayDisable = false)
    }

    private fun course() {
        loading(idService = COURSE_FIREBASE)
    }

    private fun theme() {
        loading(idService = THEME_FIREBASE, isDelayDisable = false)
    }

    private fun level() {
        loading(idService = LEVEL_FIREBASE, isDelayDisable = false)
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

    private fun subThemeFirebase() {
        theme.subThemeFirebase(idCourse = course.id, success = { result ->
            value(action = ExerciseGraphicIntentAction(subThemeArrayList = result))
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

    private fun levelFirebase() {
        subTheme.levelFirebase(idCourse = course.id, idTheme = theme.id, success = { result ->
            levelArrayList.clear()
            levelArrayList.addAll(result)

            if (levelArrayList.isEmpty()) {
                value(
                    action = ExerciseGraphicIntentAction(
                        noteLeveOneArrayList = arrayListOf(),
                        noteLeveTwoArrayList = arrayListOf(),
                        noteArrayList = arrayListOf()
                    )
                )
            } else {
                loading(idService = EXERCISE_FIREBASE, isDelayDisable = false)
            }
        }, error = {
            emit(
                event = ExerciseGraphicIntentEvent.AlertMessage(
                    alertMessage = AlertMessage(idMessage = R.string.not_error_service_firebase_message)
                )
            )
            loading()
        })
    }

    private fun exerciseFirebase() {
        student.noteFirebaseArrayList(
            idCollection = com.leandro1995.seito.fcm.firestore.config.Setting.EXERCISE,
            success = { result ->
                if (result.isEmpty()) {
                    value(
                        action = ExerciseGraphicIntentAction(
                            noteLeveOneArrayList = arrayListOf(),
                            noteLeveTwoArrayList = arrayListOf(),
                            noteArrayList = arrayListOf()
                        )
                    )
                } else {
                    result.groupBy { it.idGroup }.let { noteGroup ->
                        value(
                            action = ExerciseGraphicIntentAction(
                                noteLeveOneArrayList = ArrayList(
                                    noteGroup[levelArrayList.getOrNull(0)?.id] ?: arrayListOf()
                                ),
                                noteLeveTwoArrayList = ArrayList(
                                    noteGroup[levelArrayList.getOrNull(1)?.id] ?: arrayListOf()
                                ),
                                noteArrayList = ArrayList(result.filter { note -> note.idGroup in levelArrayList.map { it.id } })
                            )
                        )
                    }
                }
                loading()
            },
            error = {
                value(
                    action = ExerciseGraphicIntentAction(
                        noteLeveOneArrayList = arrayListOf(),
                        noteLeveTwoArrayList = arrayListOf(),
                        noteArrayList = arrayListOf()
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
        const val SUB_THEME = 2
        const val LEVEL = 3
        private const val COURSE_FIREBASE = 4
        private const val THEME_FIREBASE = 5
        private const val SUB_THEME_FIREBASE = 6
        private const val LEVEL_FIREBASE = 7
        private const val EXERCISE_FIREBASE = 8
    }
}