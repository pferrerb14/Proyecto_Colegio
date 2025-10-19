package com.leandro1995.seito.activity

import androidx.activity.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.databinding.ActivityLoginBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.intent.callback.event.LoginIntentEventCallBack
import com.leandro1995.seito.intent.config.event.LoginIntentEventConfig
import com.leandro1995.seito.util.design.LoginUtilDesign
import com.leandro1995.seito.viewmodel.LoginViewModel

class LoginActivity : ActivityAmbient<ActivityLoginBinding>(), LoginIntentEventCallBack {
    private val loginViewModel by viewModels<LoginViewModel>()
    private val loginIntentEventConfig = LoginIntentEventConfig(loginIntentEventCallBack = this)

    override var idLayout: Int = R.layout.activity_login

    override fun initView() {
        dataBinding?.let {
            it.loginViewModel = loginViewModel
        }
    }

    override fun initEventToAction() {
        lifecycleScope {
            loginViewModel.event.collect { loginIntentEvent ->
                loginIntentEventConfig.initConfig(event = loginIntentEvent)
            }
        }
    }

    override fun studentSelect() {
        dataBinding?.let {
            LoginUtilDesign.selectType(
                context = this@LoginActivity, active = it.studentText, it.adminText, it.teacherText
            )
        }
    }

    override fun teacherSelect() {
        dataBinding?.let {
            LoginUtilDesign.selectType(
                context = this@LoginActivity, active = it.teacherText, it.adminText, it.studentText
            )
        }
    }

    override fun adminSelect() {
        dataBinding?.let {
            LoginUtilDesign.selectType(
                context = this@LoginActivity, active = it.adminText, it.studentText, it.teacherText
            )
        }
    }
}