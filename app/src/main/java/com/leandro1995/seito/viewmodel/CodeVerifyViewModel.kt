package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.event.CodeVerifyIntentEvent
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
        
    }

    companion object {
        const val CODE_VERIFY = 0
    }
}