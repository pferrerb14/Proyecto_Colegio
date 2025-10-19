package com.leandro1995.seito.activity

import androidx.activity.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.databinding.ActivityLoginBinding
import com.leandro1995.seito.intent.callback.event.LoginIntentEventCallBack
import com.leandro1995.seito.viewmodel.LoginViewModel

class LoginActivity : ActivityAmbient<ActivityLoginBinding>(), LoginIntentEventCallBack {

    private val loginViewModel by viewModels<LoginViewModel>()

    override var idLayout: Int = R.layout.activity_login

    override fun initView() {
        dataBinding?.let {
            it.loginViewModel = loginViewModel
        }
    }
}