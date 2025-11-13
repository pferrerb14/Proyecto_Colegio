package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.SplashIntentAction
import com.leandro1995.seito.protodatastore.config.UserProtoDataStoreConfig
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class SplashViewModel : ViewModelAmbient<SplashIntentAction, Any>() {

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
}