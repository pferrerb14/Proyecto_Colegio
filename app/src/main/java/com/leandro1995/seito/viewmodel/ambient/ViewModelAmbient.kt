package com.leandro1995.seito.viewmodel.ambient

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

open class ViewModelAmbient<A, E> : ViewModel() {
    
    private val uiAction: MutableStateFlow<A?> by lazy {
        MutableStateFlow(null)
    }

    private val uiEvent: MutableSharedFlow<E> by lazy {
        MutableSharedFlow()
    }

    val action = uiAction.asStateFlow()

    val event = uiEvent.asSharedFlow()

    protected val actionButton = fun(action: Int) { event(action = action) }

    protected fun value(action: A) {
        uiAction.value = action
    }

    protected fun emit(event: E) {
        uiEvent.tryEmit(event)
    }

    protected open fun event(action: Int) {}
}