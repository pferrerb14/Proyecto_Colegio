package com.leandro1995.seito.intent.event

import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient

sealed class ThemeAddIntentEvent {
    data object TopicEditorBottomSheet : ThemeAddIntentEvent()
    data class Loading(val loadingIntentEventAmbient: LoadingIntentEventAmbient) :
        ThemeAddIntentEvent()
}