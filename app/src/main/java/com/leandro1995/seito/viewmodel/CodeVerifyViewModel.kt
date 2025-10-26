package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.intent.event.CodeVerifyIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class CodeVerifyViewModel : ViewModelAmbient<Any, CodeVerifyIntentEvent>() {

    val teacher = Teacher()

    override fun event(action: Int) {
        when (action) {
            CODE_VERIFY -> {
                codeVerify()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            CODE_VERIFY_FIREBASE -> {
                codeVerifyFirebase()
            }
        }
    }

    private fun codeVerify() {
        when {
            teacher.isCode() -> {
                emit(
                    event = CodeVerifyIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.no_code_message
                        )
                    )
                )
            }

            !teacher.isCodeLength(length = Setting.CODE_LENGTH) -> {
                emit(
                    event = CodeVerifyIntentEvent.AlertMessage(
                        alertMessage = AlertMessage(
                            idMessage = R.string.no_code_length_message
                        )
                    )
                )
            }

            else -> {
                loading(idService = CODE_VERIFY_FIREBASE)
            }
        }
    }

    private fun codeVerifyFirebase() {
        teacher.codeVerifyFirebase(success = {

        }, error = {
            emit(event = CodeVerifyIntentEvent.AlertMessage(alertMessage = AlertMessage(idMessage = R.string.code_error_message)))
        })
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = CodeVerifyIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    loading = Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    companion object {
        const val CODE_VERIFY = 0
        const val CODE_VERIFY_FIREBASE = 1
    }
}