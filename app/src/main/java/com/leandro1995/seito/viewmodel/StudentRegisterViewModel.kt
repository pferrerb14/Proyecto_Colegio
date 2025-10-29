package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.intent.action.StudentRegisterIntentAction
import com.leandro1995.seito.intent.event.StudentRegisterIntentEvent
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class StudentRegisterViewModel :
    ViewModelAmbient<StudentRegisterIntentAction, StudentRegisterIntentEvent>() {

    val teacher = Teacher()
    val student = Student()
    var confirmPassword = ""

    override fun event(action: Int) {
        when (action) {
            STUDENT_VALIDATION -> {
                studentRegister()
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
        }
    }

    companion object {
        const val STUDENT_VALIDATION = 0
    }
}