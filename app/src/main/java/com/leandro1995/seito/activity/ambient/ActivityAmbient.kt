package com.leandro1995.seito.activity.ambient

import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.leandro1995.seito.extension.binding


abstract class ActivityAmbient<binding> : AppCompatActivity() {

    protected var dataBinding: binding? = null

    protected abstract var idLayout: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentView()
        initEventToAction()
        fullScreen()
    }

    private fun fullScreen() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.BAKLAVA && !isGestureNavigation()) {
            enableEdgeToEdge()
            ViewCompat.setOnApplyWindowInsetsListener(
                findViewById<ViewGroup>(android.R.id.content).getChildAt(
                    0
                )
            ) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }
        }
    }

    private fun isGestureNavigation(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            return false
        }

        return try {
            Settings.Secure.getInt(this.contentResolver, NAVIGATION_MODE) == 2
        } catch (_: Settings.SettingNotFoundException) {
            false
        }
    }

    private fun contentView() {
        if (idLayout != -1) {
            if (dataBinding == null) {
                dataBinding = binding(idLayout = idLayout)
            }
        }
    }

    open fun initView() {}

    open fun initEventToAction() {}

    companion object {
        private const val NAVIGATION_MODE = "navigation_mode"
    }
}