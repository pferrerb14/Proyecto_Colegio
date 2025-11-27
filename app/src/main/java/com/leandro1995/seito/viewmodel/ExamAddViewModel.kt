package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.ExamAddIntentAction
import com.leandro1995.seito.intent.event.ExamAddIntentEvent
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ExamAddViewModel : ViewModelAmbient<ExamAddIntentAction, ExamAddIntentEvent>()