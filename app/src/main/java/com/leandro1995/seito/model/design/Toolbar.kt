package com.leandro1995.seito.model.design

import androidx.annotation.StringRes
import com.google.android.material.appbar.MaterialToolbar
import com.leandro1995.seito.R

class Toolbar(
    private val materialToolbar: MaterialToolbar,
    @param:StringRes private val idTitle: Int = -1,
    private val titleText: String = "",
    private val isArrow: Boolean = false,
    private val icArrow: Int = R.drawable.ic_arrow
) {
    fun config(method: () -> Unit = {}) {
        materialToolbar.apply {
            title = when {
                titleText.isNotEmpty() -> {
                    titleText
                }

                idTitle != -1 -> {
                    context.getString(idTitle)
                }

                else -> {
                    ""
                }
            }
            if (isArrow) {
                setNavigationIcon(icArrow)
            }
            setNavigationOnClickListener { method() }
        }
    }
}