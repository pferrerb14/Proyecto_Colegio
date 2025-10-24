package com.leandro1995.seito

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.leandro1995.seito.activity.LoginActivity
import com.leandro1995.seito.background.coroutine.BackGroundCoroutine
import com.leandro1995.seito.background.coroutine.setting.TimeTypeCoroutine

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition { true }

        BackGroundCoroutine(
            time = TIME_SKIP, timeTypeCoroutine = TimeTypeCoroutine.SECONDS
        ).start {
            startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            finishAffinity()
        }
    }

    companion object {
        private const val TIME_SKIP = 5L
    }
}