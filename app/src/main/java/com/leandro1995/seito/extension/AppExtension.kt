package com.leandro1995.seito.extension

import android.app.Activity
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

fun <T : ViewDataBinding> Activity.binding(@LayoutRes idLayout: Int) =
    DataBindingUtil.setContentView<T>(this, idLayout)