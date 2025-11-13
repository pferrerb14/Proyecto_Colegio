package com.leandro1995.seito.activity.ambient

import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.ViewGroup
import android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.leandro1995.seito.extension.binding

abstract class ActivityAmbient<binding> : AppCompatActivity() {

    protected var dataBinding: binding? = null

    protected abstract var idLayout: Int

    open var isStatusBarColorIcon: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentView()
        initView()
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
                v.setPadding(0, 0, systemBars.right, systemBars.bottom)
                insets
            }
        }

        statusBarColorIcon()
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

    private fun statusBarColorIcon() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (isStatusBarColorIcon) {
                window.decorView.getWindowInsetsController()?.setSystemBarsAppearance(
                    APPEARANCE_LIGHT_STATUS_BARS, APPEARANCE_LIGHT_STATUS_BARS
                )
            } else {
                window.decorView.getWindowInsetsController()
                    ?.setSystemBarsAppearance(0, APPEARANCE_LIGHT_STATUS_BARS)
            }
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