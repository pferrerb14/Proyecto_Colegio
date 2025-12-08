package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.AssistantIntentAction
import com.leandro1995.seito.intent.event.AssistantIntentEvent
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class AssistantViewModel : ViewModelAmbient<AssistantIntentAction, AssistantIntentEvent>()