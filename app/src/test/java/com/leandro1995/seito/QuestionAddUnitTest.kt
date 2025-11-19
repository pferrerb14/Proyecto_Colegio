package com.leandro1995.seito

import com.leandro1995.seito.ambient.TestAmbient
import com.leandro1995.seito.intent.event.QuestionAddIntentEvent
import com.leandro1995.seito.model.entity.Option
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

    @Test
    fun isOptionArrayList() = runBlocking {
        questionAddViewModel.question.name = "¿Pregunta de prueba 1?"
        test<QuestionAddIntentEvent>(sharedFlow = questionAddViewModel.event, action = {
            questionAddViewModel.button.invoke(QuestionAddViewModel.QUESTION_VALIDATION)
        })
    }

    @Test
    fun optionLength() = runBlocking {
        questionAddViewModel.question.apply {
            name = "¿Pregunta de prueba 1?"
            optionArrayList.add(Option(false, "Opcion 1"))
            optionArrayList.add(Option(false, "Opcion 2"))
        }
        test<QuestionAddIntentEvent>(sharedFlow = questionAddViewModel.event, action = {
            questionAddViewModel.button.invoke(QuestionAddViewModel.QUESTION_VALIDATION)
        })
    }

    @Test
    fun isCoin() = runBlocking {
        questionAddViewModel.question.apply {
            name = "¿Pregunta de prueba 1?"
            optionArrayList.add(Option(false, "Opcion 1"))
            optionArrayList.add(Option(false, "Opcion 2"))
            optionArrayList.add(Option(false, "Opcion 3"))
            optionArrayList.add(Option(false, "Opcion 4"))
            optionArrayList.add(Option(false, "Opcion 5"))
        }
        test<QuestionAddIntentEvent>(sharedFlow = questionAddViewModel.event, action = {
            questionAddViewModel.button.invoke(QuestionAddViewModel.QUESTION_VALIDATION)
        })
    }
}