package com.leandro1995.seito

import com.leandro1995.seito.ambient.TestAmbient
import com.leandro1995.seito.intent.event.QuestionAddIntentEvent
import com.leandro1995.seito.viewmodel.QuestionAddViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test

class QuestionAddUnitTest : TestAmbient() {

    private val questionAddViewModel = QuestionAddViewModel()

    @Test
    fun isEmptyName() = runBlocking {
        test<QuestionAddIntentEvent>(sharedFlow = questionAddViewModel.event, action = {
            questionAddViewModel.button.invoke(QuestionAddViewModel.QUESTION_VALIDATION)
        })
    }
}