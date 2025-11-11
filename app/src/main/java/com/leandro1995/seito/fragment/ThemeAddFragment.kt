package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.databinding.FragmentThemeAddBinding
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.viewmodel.ThemeAddViewModel

class ThemeAddFragment : FragmentAmbient<FragmentThemeAddBinding>() {

    private val themeAddViewModel by viewModels<ThemeAddViewModel>()

    override var idLayout: Int = R.layout.fragment_theme_add

    override fun initView() {
        dataBinding?.themeAddViewModel = themeAddViewModel
    }
}