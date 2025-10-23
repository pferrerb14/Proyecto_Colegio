package com.leandro1995.seito.intent.event.ambient

sealed class LoadingIntentEventAmbient {

    data class Loading(val loading: com.leandro1995.seito.component.model.Loading) :
        LoadingIntentEventAmbient()
}