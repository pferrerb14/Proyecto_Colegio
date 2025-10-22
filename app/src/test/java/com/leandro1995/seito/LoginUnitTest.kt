package com.leandro1995.seito

import com.leandro1995.seito.ambient.TestAmbient
import com.leandro1995.seito.intent.event.LoginIntentEvent
import com.leandro1995.seito.viewmodel.LoginViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test

class LoginUnitTest : TestAmbient() {

    private val loginViewModel = LoginViewModel()

    @Test
    fun isEmptyField() = runBlocking {
        test<LoginIntentEvent.AlertMessage>(sharedFlow = loginViewModel.event, action = {
            loginViewModel.actionButton.invoke(LoginViewModel.LOGIN_VALIDATION)
        })
    }
}