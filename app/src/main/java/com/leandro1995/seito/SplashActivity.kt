package com.leandro1995.seito

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.leandro1995.seito.background.coroutine.BackGroundCoroutine
import com.leandro1995.seito.background.coroutine.setting.TypeTimeCoroutine

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition { true }

        BackGroundCoroutine(
            time = TIME_SKIP, typeTimeCoroutine = TypeTimeCoroutine.SECONDS
        ).start {

        }
    }

    companion object {
        private const val TIME_SKIP = 5L
    }
}