package com.leandro1995.seito

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.leandro1995.seito.activity.HomeStudentActivity
import com.leandro1995.seito.activity.LoginActivity
import com.leandro1995.seito.background.coroutine.BackGroundCoroutine
import com.leandro1995.seito.background.coroutine.setting.TimeTypeCoroutine
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.intent.callback.action.SplashIntentActionCallBack
import com.leandro1995.seito.intent.config.action.SplashIntentActionConfig
import com.leandro1995.seito.viewmodel.SplashViewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(), SplashIntentActionCallBack {

    private val splashViewModel by viewModels<SplashViewModel>()
    private val splashIntentActionConfig =
        SplashIntentActionConfig(splashIntentActionCallBack = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition { true }

        lifecycleScope {
            splashViewModel.action.collect { splashIntentAction ->
                splashIntentActionConfig.initConfig(event = splashIntentAction)
            }
        }

        splashViewModel.button.invoke(SplashViewModel.VALIDATION_DIRECT)
    }

    override fun validationDirect() {
        BackGroundCoroutine(
            time = TIME_SKIP, timeTypeCoroutine = TimeTypeCoroutine.SECONDS
        ).start {
            splashViewModel.service(idService = 0)
        }
    }

    override fun login() {
        startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
        finishAffinity()
    }

    override fun home() {
        startActivity(Intent(this@SplashActivity, HomeStudentActivity::class.java))
        finishAffinity()
    }

    companion object {
        private const val TIME_SKIP = 2L
    }
}