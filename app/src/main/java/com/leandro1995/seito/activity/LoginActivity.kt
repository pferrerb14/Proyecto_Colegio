package com.leandro1995.seito.activity

import android.content.Intent
import androidx.activity.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.databinding.ActivityLoginBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.intent.callback.event.LoginLoadingIntentEventCallBack
import com.leandro1995.seito.intent.config.event.LoginIntentEventConfig
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.util.design.LoginUtilDesign
import com.leandro1995.seito.util.dialog.AppUtilDialog
import com.leandro1995.seito.viewmodel.LoginViewModel

class LoginActivity : ActivityAmbient<ActivityLoginBinding>(), LoginLoadingIntentEventCallBack {
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
                context = this@LoginActivity, active = it.studentText, deactivated = it.teacherText
            )
        }
    }

    override fun teacherSelect() {
        dataBinding?.let {
            LoginUtilDesign.selectType(
                context = this@LoginActivity, active = it.teacherText, deactivated = it.studentText
            )
        }
    }

    override fun alertMessage(alertMessage: AlertMessage) {
        AppUtilDialog.dialogMaterialDesign(
            context = this@LoginActivity, alertMessage = alertMessage
        )
    }

    override fun homeActivity() {
        startActivity(Intent(this, HomeActivity::class.java))
        finishAffinity()
    }

    override fun loading(loading: Loading) {
        dataBinding?.loadingComponent?.startService(loading = loading) {
            loginViewModel.service(idService = loading.idService)
        }
    }
}