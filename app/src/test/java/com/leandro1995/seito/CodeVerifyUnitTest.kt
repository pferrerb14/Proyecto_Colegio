package com.leandro1995.seito

import com.leandro1995.seito.ambient.TestAmbient
import com.leandro1995.seito.intent.event.CodeVerifyIntentEvent
import com.leandro1995.seito.viewmodel.CodeVerifyViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CodeVerifyUnitTest : TestAmbient() {

    private val codeVerifyViewModel = CodeVerifyViewModel()

    @Test
    fun isEmptyCode() = runBlocking {
        test<CodeVerifyIntentEvent.AlertMessage>(sharedFlow = codeVerifyViewModel.event, action = {
            codeVerifyViewModel.button.invoke(CodeVerifyViewModel.CODE_VERIFY)
        })
    }

    @Test
    fun isEmptyCodeLength() = runBlocking {
        codeVerifyViewModel.teacher.apply {
            code = "12"
        }
        test<CodeVerifyIntentEvent.AlertMessage>(sharedFlow = codeVerifyViewModel.event, action = {
            codeVerifyViewModel.button.invoke(CodeVerifyViewModel.CODE_VERIFY)
        })
    }
}