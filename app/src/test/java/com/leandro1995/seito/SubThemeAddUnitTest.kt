package com.leandro1995.seito

import com.leandro1995.seito.ambient.TestAmbient
import com.leandro1995.seito.intent.event.SubThemeAddIntentEvent
import com.leandro1995.seito.viewmodel.SubThemeAddViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test

class SubThemeAddUnitTest : TestAmbient() {

    private val subThemeViewModel = SubThemeAddViewModel()

    @Test
    fun isEmptyThemeName() = runBlocking {
        test<SubThemeAddIntentEvent.AlertMessage>(sharedFlow = subThemeViewModel.event, action = {
            subThemeViewModel.button.invoke(SubThemeAddViewModel.NAME_SUB_THEME_VALIDATION)
        })
    }
}