package com.leandro1995.seito.activity

import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.databinding.ActivityThemeBinding
import com.leandro1995.seito.model.design.Toolbar

class ThemeActivity : ActivityAmbient<ActivityThemeBinding>() {

    override var idLayout: Int = R.layout.activity_theme

    override fun initView() {
        dataBinding?.apply {
            Toolbar(
                context = this@ThemeActivity,
                materialToolbar = appBarBlueInclude.toolbar,
                idTitle = R.string.theme_title,
                icArrow = R.drawable.ic_arrow_white,
                isArrow = true
            ).config { finish() }
        }
    }
}