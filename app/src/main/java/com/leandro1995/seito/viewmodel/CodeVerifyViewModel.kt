package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.intent.event.CodeVerifyIntentEvent
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class CodeVerifyViewModel : ViewModelAmbient<Any, CodeVerifyIntentEvent>() {

    private val teacher = Teacher()

    override fun event(action: Int) {
        when (action) {
            CODE_VERIFY -> {
                codeVerify()
            }
        }
    }

    private fun codeVerify() {
        if (!teacher.isCode()) {

        } else {
            emit(event = CodeVerifyIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.code_error_message)))
        }
    }

    companion object {
        const val CODE_VERIFY = 0
    }
}