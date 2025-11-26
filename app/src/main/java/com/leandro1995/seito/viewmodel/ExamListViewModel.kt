package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.ExamListIntentAction
import com.leandro1995.seito.intent.event.ExamListIntentEvent
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ExamListViewModel : ViewModelAmbient<ExamListIntentAction, ExamListIntentEvent>()