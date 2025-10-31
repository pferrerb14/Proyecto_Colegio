package com.leandro1995.seito.intent.event

sealed class ProfileIntentEvent {
    data object CleanProtoDataStore : ProfileIntentEvent()
}