package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentSubThemeLevelBinding
import com.leandro1995.seito.extension.argumentParcelable
import com.leandro1995.seito.extension.argumentString
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.SubThemeLevelIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.SubThemeLevelIntentEventCallBack
import com.leandro1995.seito.intent.config.action.SubThemeLevelIntentActionConfig
import com.leandro1995.seito.intent.config.event.SubThemeLevelIntentEventConfig
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Level
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.viewmodel.SubThemeLevelViewModel

class SubThemeLevelFragment : FragmentAmbient<FragmentSubThemeLevelBinding>(),
    SubThemeLevelIntentActionCallBack, SubThemeLevelIntentEventCallBack {

    private val subThemeLevelViewModel by viewModels<SubThemeLevelViewModel>()
    private val subThemeLevelIntentEventConfig =
        SubThemeLevelIntentEventConfig(subThemeLevelIntentEventCallBack = this)
    private val subThemeLevelIntentActionConfig =
        SubThemeLevelIntentActionConfig(subthemeLevelIntentActionCallBack = this)

    override var idLayout: Int = R.layout.fragment_sub_theme_level

    override fun initView() {
        dataBinding?.subThemeLevelViewModel = subThemeLevelViewModel
    }

    override fun initEventToAction() {
        lifecycleScope {
            subThemeLevelViewModel.event.collect { subThemeLevelIntentEvent ->
                subThemeLevelIntentEventConfig.initConfig(event = subThemeLevelIntentEvent)
            }
        }

        lifecycleScope {
            subThemeLevelViewModel.action.collect { subThemeLevelIntentAction ->
                subThemeLevelIntentActionConfig.initConfig(event = subThemeLevelIntentAction)
            }
        }
    }

    override fun arguments() {
        Setting.ID_COURSE_BUNDLE.argumentString(bundle = arguments)?.let {
            subThemeLevelViewModel.idCourse = it
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

    override fun loading(loading: Loading) {
        dataBinding?.loadingComponent?.startService(loading = loading) {
            subThemeLevelViewModel.service(idService = loading.idService)
        }
    }

    override fun startService() {
        subThemeLevelViewModel.button.invoke(SubThemeLevelViewModel.LEVEL)
    }

    override fun levelArrayList(levelArrayList: ArrayList<Level>) {
        dataBinding?.subThemeLevelVerticalComponentList?.setAdapter(arrayList = levelArrayList)
    }
}