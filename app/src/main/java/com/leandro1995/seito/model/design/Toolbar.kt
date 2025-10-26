package com.leandro1995.seito.model.design

import android.content.Context
import androidx.annotation.StringRes
import com.google.android.material.appbar.MaterialToolbar
import com.leandro1995.seito.R

class Toolbar(
    private val context: Context,
    private val materialToolbar: MaterialToolbar,
    @param:StringRes private val idTitle: Int,
    private val isArrow: Boolean = false
) {
    fun config(method: () -> Unit) {
        materialToolbar.apply {
            title = context.getString(idTitle)
            if (isArrow) {
                setNavigationIcon(R.drawable.ic_arrow)
            }
            setNavigationOnClickListener { method() }
        }
    }
}