package com.leandro1995.seito.background.coroutine

import com.leandro1995.seito.background.coroutine.setting.TypeTimeCoroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

class BackGroundCoroutine(
    private val context: CoroutineContext = Dispatchers.IO,
    private val time: Long,
    private val typeTimeCoroutine: TypeTimeCoroutine
) {

    fun start(method: suspend () -> Unit) {
        CoroutineScope(context).launch {
            delay(time())
            method()
        }
    }

    private fun time() = when (typeTimeCoroutine) {
        TypeTimeCoroutine.HOURS -> TimeUnit.HOURS.toMillis(time)
        TypeTimeCoroutine.MINUTES -> TimeUnit.MINUTES.toMillis(time)
        TypeTimeCoroutine.SECONDS -> TimeUnit.SECONDS.toMillis(time)
    }
}