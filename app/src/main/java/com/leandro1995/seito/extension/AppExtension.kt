package com.leandro1995.seito.extension

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch

fun <T : ViewDataBinding> Activity.binding(@LayoutRes idLayout: Int): T? =
    DataBindingUtil.setContentView<T>(this, idLayout)

fun Activity.lifecycleScope(method: suspend () -> Unit) {
    (this as AppCompatActivity).lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            method()
        }
    }
}