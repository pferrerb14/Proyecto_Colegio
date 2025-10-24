package com.leandro1995.seito.ambient

import app.cash.turbine.test
import kotlinx.coroutines.flow.SharedFlow
import org.junit.Assert.assertTrue

open class TestAmbient {

    protected suspend inline fun <reified T> test(
        sharedFlow: SharedFlow<*>, crossinline action: () -> Unit
    ) {
        sharedFlow.test {
            action()
            assertTrue(awaitItem() is T)
            cancelAndIgnoreRemainingEvents()
        }
    }
}