package com.leandro1995.seito.viewmodel

import android.util.Log
import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.event.LoginIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.ambient.User
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class LoginViewModel : ViewModelAmbient<Any, LoginIntentEvent>() {

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
                loading(idService = LOGIN_FIREBASE)
            }
        }
    }

    private fun loginFirebase() {
        user.loginFirebase(success = { email ->
            Log.e("ENTRAA", "SIIIII")
        }, error = {
            emit(event = LoginIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.no_user_message)))
            loading()
        })
    }

    override fun loading(idService: Int) {
        emit(
            event = LoginIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    Loading(idService = idService)
                )
            )
        )
    }

    companion object {
        const val STUDENT_TYPE = 0
        const val TEACHER_TYPE = 1
        const val ADMIN_TYPE = 2
        const val LOGIN_VALIDATION = 3
        private const val LOGIN_FIREBASE = 4
    }
}