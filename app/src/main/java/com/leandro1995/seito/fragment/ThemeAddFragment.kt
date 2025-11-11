package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentThemeAddBinding
import com.leandro1995.seito.extension.parcelable
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.viewmodel.ThemeAddViewModel

class ThemeAddFragment : FragmentAmbient<FragmentThemeAddBinding>() {

    private val themeAddViewModel by viewModels<ThemeAddViewModel>()

    override var idLayout: Int = R.layout.fragment_theme_add

    override fun initView() {
        dataBinding?.themeAddViewModel = themeAddViewModel
    }

    override fun putExtra() {
        Setting.COURSE_PUT_EXTRA.parcelable<Course>(activity = requireActivity())?.let {
            themeAddViewModel.course = it
        }

        dataBinding?.let {
            Toolbar(
                materialToolbar = it.appBarBlueInclude.toolbar,
                titleText = themeAddViewModel.course.name,
                icArrow = R.drawable.ic_arrow_white,
                isArrow = true
            ).config { requireActivity().finish() }
        }
    }
}