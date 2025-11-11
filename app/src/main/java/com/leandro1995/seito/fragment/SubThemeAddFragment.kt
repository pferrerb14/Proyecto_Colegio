package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.leandro1995.seito.R
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentSubThemeAddBinding
import com.leandro1995.seito.extension.argumentParcelable
import com.leandro1995.seito.extension.argumentString
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.viewmodel.SubThemeAddViewModel

class SubThemeAddFragment : FragmentAmbient<FragmentSubThemeAddBinding>() {

    private val subThemeViewModel by viewModels<SubThemeAddViewModel>()

    override var idLayout: Int = R.layout.fragment_sub_theme_add

    override fun initView() {
        dataBinding?.subThemeViewModel = subThemeViewModel
    }

    override fun arguments() {
        Setting.ID_COURSE_BUNDLE.argumentString(bundle = arguments)?.let {
            subThemeViewModel.idCourse = it
        }

        Setting.THEME_BUNDLE.argumentParcelable<Theme>(bundle = arguments)?.let {
            subThemeViewModel.theme = it
        }

        dataBinding?.appBarBlueInclude?.toolbar?.let {
            Toolbar(
                materialToolbar = it,
                titleText = subThemeViewModel.theme.name,
                icArrow = R.drawable.ic_arrow_white,
                isArrow = true
            ).config { findNavController().popBackStack() }
        }
    }
}