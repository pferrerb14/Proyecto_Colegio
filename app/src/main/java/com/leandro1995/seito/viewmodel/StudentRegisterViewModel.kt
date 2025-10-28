package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.StudentRegisterIntentAction
import com.leandro1995.seito.intent.event.StudentRegisterIntentEvent
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class StudentRegisterViewModel :
    ViewModelAmbient<StudentRegisterIntentAction, StudentRegisterIntentEvent>()