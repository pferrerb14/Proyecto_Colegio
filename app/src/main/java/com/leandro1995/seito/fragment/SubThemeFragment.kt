package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentSubThemeBinding
import com.leandro1995.seito.extension.argumentParcelable
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.SubThemeIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.SubThemeIntentEventCallBack
import com.leandro1995.seito.intent.config.action.SubThemeIntentActionConfig
import com.leandro1995.seito.intent.config.event.SubThemeIntentEventConfig
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.viewmodel.SubThemeViewModel

class SubThemeFragment : FragmentAmbient<FragmentSubThemeBinding>(), SubThemeIntentEventCallBack,
    SubThemeIntentActionCallBack {

    private val subThemeViewModel by viewModels<SubThemeViewModel>()
    private val subThemeIntentEventConfig =
        SubThemeIntentEventConfig(subThemeIntentEventCallBack = this)
    private val subThemeIntentActionConfig =
        SubThemeIntentActionConfig(subThemeIntentActionCallBack = this)

    override var idLayout: Int = R.layout.fragment_sub_theme

    override fun initView() {
        dataBinding?.subThemeViewModel = subThemeViewModel
    }

    override fun initEventToAction() {
        lifecycleScope {
            subThemeViewModel.event.collect { subThemeIntentEvent ->
                subThemeIntentEventConfig.initConfig(event = subThemeIntentEvent)
            }
        }

        lifecycleScope {
            subThemeViewModel.action.collect { subThemeIntentAction ->
                subThemeIntentActionConfig.initConfig(event = subThemeIntentAction)
            }
        }
    }

    override fun arguments() {
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