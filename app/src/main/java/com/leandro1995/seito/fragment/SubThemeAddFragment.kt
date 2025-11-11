package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentSubThemeAddBinding
import com.leandro1995.seito.extension.argumentParcelable
import com.leandro1995.seito.extension.argumentString
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.SubThemeAddIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.SubThemeAddIntentEventCallBack
import com.leandro1995.seito.intent.config.action.SubThemeAddIntentActionConfig
import com.leandro1995.seito.intent.config.event.SubThemeAddIntentEventConfig
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.viewmodel.SubThemeAddViewModel

class SubThemeAddFragment : FragmentAmbient<FragmentSubThemeAddBinding>(),
    SubThemeAddIntentActionCallBack, SubThemeAddIntentEventCallBack {

    private val subThemeViewModel by viewModels<SubThemeAddViewModel>()
    private val subThemeAddIntentActionConfig =
        SubThemeAddIntentActionConfig(subThemeAddIntentActionCallBack = this)
    private val subThemeAddIntentEventConfig =
        SubThemeAddIntentEventConfig(subThemeAddIntentEventCallBack = this)

    override var idLayout: Int = R.layout.fragment_sub_theme_add

    override fun initView() {
        dataBinding?.subThemeViewModel = subThemeViewModel
    }

    override fun initEventToAction() {
        lifecycleScope {
            subThemeViewModel.event.collect { subThemeAddIntentEvent ->
                subThemeAddIntentEventConfig.initConfig(event = subThemeAddIntentEvent)
            }
        }

        lifecycleScope {
            subThemeViewModel.action.collect { subThemeAddIntentAction ->
                subThemeAddIntentActionConfig.initConfig(event = subThemeAddIntentAction)
            }
        }
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

    override fun loading(loading: Loading) {
        dataBinding?.loadingComponent?.startService(loading = loading) {
            subThemeViewModel.service(idService = loading.idService)
        }
    }
}