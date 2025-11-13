package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.SplashIntentAction
import com.leandro1995.seito.protodatastore.config.UserProtoDataStoreConfig
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class SplashViewModel : ViewModelAmbient<SplashIntentAction, Any>() {

    override fun event(action: Int) {
        when (action) {
            VALIDATION_DIRECT -> {
                validationDirect()
            }
        }
    }

    override suspend fun service(idService: Int) {
        if (UserProtoDataStoreConfig.getName().isEmpty()) {
            value(action = SplashIntentAction(isLoginDirect = true))
        } else {
            if (UserProtoDataStoreConfig.getIsUserType()) {
                value(action = SplashIntentAction(isStudentHomeDirect = true))
            } else {
                value(action = SplashIntentAction(isTeacherHomeDirect = true))
            }
        }
    }

    private fun validationDirect() {
        value(action = SplashIntentAction(isValidationDirect = true))
    }

    companion object {
        const val VALIDATION_DIRECT = 0
    }
}