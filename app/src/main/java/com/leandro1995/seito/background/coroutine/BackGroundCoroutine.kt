package com.leandro1995.seito.background.coroutine

import com.leandro1995.seito.background.coroutine.setting.TimeTypeCoroutine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import kotlin.coroutines.CoroutineContext

class BackGroundCoroutine(
    private val context: CoroutineContext = Dispatchers.Main,
    private val time: Long = -1L,
    private val timeTypeCoroutine: TimeTypeCoroutine? = null,
    var isDelayDisable: Boolean = true
) {

    fun start(method: suspend () -> Unit) {
        CoroutineScope(context).launch {
            if (isDelayDisable || time == -1L) {
                delay(time())
            }
            method()
        }
    }

    private fun time() = when (timeTypeCoroutine) {
        TimeTypeCoroutine.HOURS -> TimeUnit.HOURS.toMillis(time)
        TimeTypeCoroutine.MINUTES -> TimeUnit.MINUTES.toMillis(time)
        TimeTypeCoroutine.SECONDS -> TimeUnit.SECONDS.toMillis(time)
        null -> {
            -1L
        }
    }
}