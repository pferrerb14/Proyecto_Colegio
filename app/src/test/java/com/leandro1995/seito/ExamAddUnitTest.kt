package com.leandro1995.seito

import com.leandro1995.seito.ambient.TestAmbient
import com.leandro1995.seito.intent.event.ExamAddIntentEvent
import com.leandro1995.seito.viewmodel.ExamAddViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ExamAddUnitTest : TestAmbient() {

    private val examAddViewModel = ExamAddViewModel()

    @Test
    fun isNamEmpty() = runBlocking {
        test<ExamAddIntentEvent.AlertMessage>(sharedFlow = examAddViewModel.event, action = {
            examAddViewModel.button.invoke(ExamAddViewModel.EXM_VALIDATION)
        })
    }
}