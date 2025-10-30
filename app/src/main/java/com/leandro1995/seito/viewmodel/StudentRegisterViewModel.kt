package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.extension.capsSentences
import com.leandro1995.seito.intent.action.StudentRegisterIntentAction
import com.leandro1995.seito.intent.event.StudentRegisterIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class StudentRegisterViewModel :
    ViewModelAmbient<StudentRegisterIntentAction, StudentRegisterIntentEvent>() {

    var teacher = Teacher()
    val student = Student()
    var confirmPassword = ""

    override fun event(action: Int) {
        when (action) {
            STUDENT_VALIDATION -> {
                studentRegister()
            }

            MALE_SELECT -> {
                maleSelect()
            }

            FEMALE_SELECT -> {
                femaleSelect()
            }

            TEACHER_DETAIL -> {
                teacherDetail()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            STUDENT_REGISTER_FIREBASE -> {
                studentRegisterFirebase()
            }

            STUDENT_CREATE_USER_FIREBASE -> {
                studentCreateUserFirebase()
            }
        }
    }

    private fun studentRegister() {
        when {
            student.isName() -> {
                emit(
                    event = StudentRegisterIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.no_empty_name_student_message
                        )
                    )
                )
            }

            student.isLastName() -> {
                emit(
                    event = StudentRegisterIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.no_empty_last_name_student_message
                        )
                    )
                )
            }

            student.isEmail() -> {
                emit(
                    event = StudentRegisterIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.no_email_message
                        )
                    )
                )
            }

            !student.isEmailFormat() -> {
                emit(
                    event = StudentRegisterIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.no_email_format_message
                        )
                    )
                )
            }

            student.isPassword() -> {
                emit(
                    event = StudentRegisterIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.no_password_message
                        )
                    )
                )
            }

            !student.isPasswordLength() -> {
                emit(
                    event = StudentRegisterIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.password_length_message
                        )
                    )
                )
            }

            confirmPassword.isEmpty() -> {
                emit(
                    event = StudentRegisterIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.no_password_message
                        )
                    )
                )
            }

            !student.isEqualPassword(confirmPassword = confirmPassword) -> {
                emit(
                    event = StudentRegisterIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.no_empty_confirm_password_student_message
                        )
                    )
                )
            }

            student.isEmptyAge() -> {
                emit(
                    event = StudentRegisterIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.no_empty_age_student_message
                        )
                    )
                )
            }

            !student.isAgeRange() -> {
                emit(
                    event = StudentRegisterIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.no_empty_age_validation_student_message
                        )
                    )
                )
            }

            else -> {
                loading(idService = STUDENT_REGISTER_FIREBASE)
            }
        }
    }

    private fun maleSelect() {
        emit(event = StudentRegisterIntentEvent.MaleSelect)
    }

    private fun femaleSelect() {
        emit(event = StudentRegisterIntentEvent.FemaleSelect)
    }

    private fun teacherDetail() {
        value(action = StudentRegisterIntentAction(nameTeacher = "${teacher.name} ${teacher.lastName}".capsSentences()))
    }

    private fun studentRegisterFirebase() {
        teacher.addStudentFirebase(student = student, success = {
            loading(idService = STUDENT_CREATE_USER_FIREBASE, isDelayDisable = false)
        }, error = {
            emit(
                event = StudentRegisterIntentEvent.AlertMessage(
                    alertMessage = AlertMessage(
                        idMessage = R.string.no_register_student_message
                    )
                )
            )
            loading()
        })
    }

    private fun studentCreateUserFirebase() {
        teacher.createUserStudentFirebase(student = student, success = {
            emit(
                event = StudentRegisterIntentEvent.LoginActivity(
                    alertMessage = AlertMessage(
                        idMessage = R.string.register_student_complete_message, isCancelable = false
                    )
                )
            )
            loading()
        }, error = {
            emit(
                event = StudentRegisterIntentEvent.AlertMessage(
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
            event = StudentRegisterIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    loading = Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    companion object {
        const val STUDENT_VALIDATION = 0
        const val MALE_SELECT = 1
        const val FEMALE_SELECT = 2
        const val TEACHER_DETAIL = 3
        private const val STUDENT_REGISTER_FIREBASE = 4
        private const val STUDENT_CREATE_USER_FIREBASE = 5
    }
}