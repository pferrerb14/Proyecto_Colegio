package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.leandro1995.seito.R
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentSubThemeLevelAddBinding
import com.leandro1995.seito.extension.argumentParcelable
import com.leandro1995.seito.extension.argumentString
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.viewmodel.SubThemeLevelAddViewModel

class SubThemeLevelAddFragment : FragmentAmbient<FragmentSubThemeLevelAddBinding>() {

    private val subThemeLevelAddViewModel by viewModels<SubThemeLevelAddViewModel>()

    override var idLayout: Int = R.layout.fragment_sub_theme_level_add

    override fun initView() {
        dataBinding?.subThemeLevelAddViewModel = subThemeLevelAddViewModel
    }

    override fun arguments() {
        Setting.ID_COURSE_BUNDLE.argumentString(bundle = arguments)?.let {
            subThemeLevelAddViewModel.idCourse = it
        }

        Setting.ID_THEME_BUNDLE.argumentString(bundle = arguments)?.let {
            subThemeLevelAddViewModel.idTheme = it
        }

        Setting.ID_SUB_THEME_BUNDLE.argumentParcelable<SubTheme>(bundle = arguments)?.let {
            subThemeLevelAddViewModel.subTheme = it
        }

        dataBinding?.appBarBlueInclude?.toolbar?.let {
            Toolbar(
                materialToolbar = it,
                titleText = subThemeLevelAddViewModel.subTheme.name,
                icArrow = R.drawable.ic_arrow_white,
                isArrow = true
            ).config { findNavController().popBackStack() }
        }
    }
}