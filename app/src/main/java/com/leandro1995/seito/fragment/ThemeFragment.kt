package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentThemeBinding
import com.leandro1995.seito.extension.parcelable
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.viewmodel.ThemeViewModel

class ThemeFragment : FragmentAmbient<FragmentThemeBinding>() {

    private val themeViewModel by viewModels<ThemeViewModel>()

    override var idLayout: Int = R.layout.fragment_theme

    override fun initView() {
        dataBinding?.themeViewModel = themeViewModel
    }

    override fun putExtra() {
        Setting.COURSE_PUT_EXTRA.parcelable<Course>(activity = requireActivity())?.let {
            themeViewModel.course = it
        }

        dataBinding?.appBarBlueInclude?.let {
            Toolbar(
                materialToolbar = it.toolbar,
                titleText = themeViewModel.course.name,
                isArrow = true,
                icArrow = R.drawable.ic_arrow_white
            ).config { requireActivity().finish() }
        }
    }
}