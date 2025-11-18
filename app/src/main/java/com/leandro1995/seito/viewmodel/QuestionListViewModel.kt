package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.QuestionListIntentAction
import com.leandro1995.seito.intent.event.QuestionListIntentEvent
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class QuestionListViewModel : ViewModelAmbient<QuestionListIntentAction, QuestionListIntentEvent>()