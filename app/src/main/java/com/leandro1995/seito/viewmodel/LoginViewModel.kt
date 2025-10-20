package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.intent.action.LoginIntentAction
import com.leandro1995.seito.intent.event.LoginIntentEvent
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.ambient.User
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class LoginViewModel : ViewModelAmbient<LoginIntentAction, LoginIntentEvent>() {

    val user = User()

    override fun event(action: Int) {
        when (action) {
            STUDENT_TYPE -> {
                studentType()
            }

            TEACHER_TYPE -> {
                teacherType()
            }

            ADMIN_TYPE -> {
                adminType()
            }

            LOGIN_VALIDATION -> {
                loginValidation()
            }
        }
    }

    private fun studentType() {
        emit(LoginIntentEvent.StudentSelect)
    }

    private fun teacherType() {
        emit(LoginIntentEvent.TeacherSelect)
    }

    private fun adminType() {
        emit(LoginIntentEvent.AdminSelect)
    }

    private fun loginValidation() {
        if (!user.isLogin()) {

        } else {
            emit(event = LoginIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.no_empty_fields_message)))
        }
    }

    companion object {
        const val STUDENT_TYPE = 0
        const val TEACHER_TYPE = 1
        const val ADMIN_TYPE = 2
        const val LOGIN_VALIDATION = 3
    }
}