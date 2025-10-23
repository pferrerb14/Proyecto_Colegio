package com.leandro1995.seito.viewmodel.ambient

import androidx.lifecycle.ViewModel
import com.leandro1995.seito.component.model.Loading
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.runBlocking

open class ViewModelAmbient<A, E> : ViewModel() {

    private val uiAction: MutableStateFlow<A?> by lazy {
        MutableStateFlow(null)
    }

    private val uiEvent: MutableSharedFlow<E> by lazy {
        MutableSharedFlow()
    }

    val action = uiAction.asStateFlow()

    val event = uiEvent.asSharedFlow()

    val actionButton = fun(action: Int) { event(action = action) }

    open suspend fun service(idService: Int) {}

    protected fun value(action: A) {
        uiAction.value = action
    }

    protected fun emit(event: E) {
        runBlocking { uiEvent.emit(event) }
    }

    protected open fun event(action: Int) {}

    protected open fun loading(loading: Loading? = null) {}
}