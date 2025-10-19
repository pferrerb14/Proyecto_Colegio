package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.event.LoginIntentEvent
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class LoginViewModel : ViewModelAmbient<Any, LoginIntentEvent>() {

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

    companion object {
        const val STUDENT_TYPE = 0
        const val TEACHER_TYPE = 1
        const val ADMIN_TYPE = 2
    }
}