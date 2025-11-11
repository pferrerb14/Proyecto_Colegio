package com.leandro1995.seito

import com.leandro1995.seito.ambient.TestAmbient
import com.leandro1995.seito.intent.event.ThemeAddIntentEvent
import com.leandro1995.seito.viewmodel.ThemeAddViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Test

class ThemeAddUnitTest : TestAmbient() {

    private val themeAddViewModel = ThemeAddViewModel()

    @Test
    fun isEmptyThemeName() = runBlocking {
        test<ThemeAddIntentEvent.AlertMessage>(sharedFlow = themeAddViewModel.event, action = {
            themeAddViewModel.button.invoke(ThemeAddViewModel.NAME_THEME_VALIDATION)
        })
    }
}