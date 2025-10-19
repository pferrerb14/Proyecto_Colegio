package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.event.LoginIntentEvent
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class LoginViewModel : ViewModelAmbient<Any, LoginIntentEvent>() {}