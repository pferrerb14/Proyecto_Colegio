package com.leandro1995.seito.viewmodel

import android.util.Log
import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
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

            LOGIN_LOADING -> {
                loginLoading()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            LOGIN_FIREBASE -> {
                loginFirebase()
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
        when {
            user.isLogin() -> {
                emit(event = LoginIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.no_empty_fields_message)))
            }

            user.isEmail() -> {
                emit(event = LoginIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.no_email_message)))
            }

            user.isPassword() -> {
                emit(event = LoginIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.no_password_message)))
            }

            !user.isEmailFormat() -> {
                emit(event = LoginIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.no_email_format_message)))
            }

            else -> {
                actionButton.invoke(LOGIN_LOADING)
            }
        }
    }

    private fun loginLoading() {
        loading(loading = Loading(idService = LOGIN_FIREBASE))
    }

    private fun loginFirebase() {
        user.loginFirebase(success = { email ->
            Log.e("ENTRAA","SIIIII")
        }, error = {
            Log.e("ENTRAA","NOOOOO")
        })

        loading()
    }

    override fun loading(loading: Loading?) {
        value(action = LoginIntentAction(loading = loading))
    }

    companion object {
        const val STUDENT_TYPE = 0
        const val TEACHER_TYPE = 1
        const val ADMIN_TYPE = 2
        const val LOGIN_VALIDATION = 3
        private const val LOGIN_LOADING = 4
        private const val LOGIN_FIREBASE = 5
    }
}