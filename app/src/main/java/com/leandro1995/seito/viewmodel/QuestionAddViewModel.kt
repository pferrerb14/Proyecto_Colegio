package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.QuestionAddIntentAction
import com.leandro1995.seito.intent.event.QuestionListIntentEvent
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class QuestionAddViewModel : ViewModelAmbient<QuestionAddIntentAction, QuestionListIntentEvent>()