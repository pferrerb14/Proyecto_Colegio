package com.leandro1995.seito.intent.event

import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient

sealed class SubThemeAddIntentEvent {
    data class Loading(val loadingIntentEventAmbient: LoadingIntentEventAmbient) :
        SubThemeAddIntentEvent()

    data object TopicEditorBottomSheet : SubThemeAddIntentEvent()
    data class AlertMessage(val alertMessage: com.leandro1995.seito.model.design.AlertMessage) :
        SubThemeAddIntentEvent()
}