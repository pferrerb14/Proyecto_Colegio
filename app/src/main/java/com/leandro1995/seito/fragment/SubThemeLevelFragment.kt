package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.leandro1995.seito.R
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentSubThemeLevelBinding
import com.leandro1995.seito.extension.argumentParcelable
import com.leandro1995.seito.extension.argumentString
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.viewmodel.SubThemeLevelViewModel

class SubThemeLevelFragment : FragmentAmbient<FragmentSubThemeLevelBinding>() {

    private val subThemeLevelViewModel by viewModels<SubThemeLevelViewModel>()

    override var idLayout: Int = R.layout.fragment_sub_theme_level

    override fun initView() {
        dataBinding?.subThemeLevelViewModel = subThemeLevelViewModel
    }

    override fun arguments() {
        Setting.ID_COURSE_BUNDLE.argumentString(bundle = arguments)?.let {
            subThemeLevelViewModel.idTheme = it
        }
        Setting.ID_THEME_BUNDLE.argumentString(bundle = arguments)?.let {
            subThemeLevelViewModel.idTheme = it
        }
        Setting.ID_SUB_THEME_BUNDLE.argumentParcelable<SubTheme>(bundle = arguments)?.let {
            subThemeLevelViewModel.subTheme = it
        }

        dataBinding?.appBarBlueInclude?.toolbar?.let {
            Toolbar(
                materialToolbar = it,
                titleText = subThemeLevelViewModel.subTheme.name,
                icArrow = R.drawable.ic_arrow_white,
                isArrow = true
            ).config { findNavController().popBackStack() }
        }
    }
}