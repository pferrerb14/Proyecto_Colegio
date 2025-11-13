package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.StudentListIntentAction
import com.leandro1995.seito.intent.event.StudentListIntentEvent
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class StudentListViewModel : ViewModelAmbient<StudentListIntentAction, StudentListIntentEvent>()