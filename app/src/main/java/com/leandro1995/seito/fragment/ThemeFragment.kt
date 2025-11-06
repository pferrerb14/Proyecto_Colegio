package com.leandro1995.seito.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.leandro1995.seito.R
import com.leandro1995.seito.component.list.callback.ThemeVerticalComponentListCallBack
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentThemeBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.extension.parcelable
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.ThemeIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.ThemeIntentEventCallBack
import com.leandro1995.seito.intent.config.action.ThemeIntentActionConfig
import com.leandro1995.seito.intent.config.event.ThemeIntentEventConfig
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.viewmodel.ThemeViewModel

class ThemeFragment : FragmentAmbient<FragmentThemeBinding>(), ThemeIntentEventCallBack,
    ThemeIntentActionCallBack, ThemeVerticalComponentListCallBack {

    private val themeViewModel by viewModels<ThemeViewModel>()
    private val themeIntentEventConfig = ThemeIntentEventConfig(themeIntentEventCallBack = this)
    private val themeIntentActionConfig = ThemeIntentActionConfig(themeIntentActionCallBack = this)

    override var idLayout: Int = R.layout.fragment_theme

    override fun initView() {
        dataBinding?.themeViewModel = themeViewModel
        dataBinding?.themeVerticalList?.themeVerticalComponentListCallBack = this
    }

    override fun initEventToAction() {
        lifecycleScope {
            themeViewModel.event.collect { themeIntentEvent ->
                themeIntentEventConfig.initConfig(event = themeIntentEvent)
            }
        }

        lifecycleScope {
            themeViewModel.action.collect { themeIntentAction ->
                themeIntentActionConfig.initConfig(event = themeIntentAction)
            }
        }
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

    override fun loading(loading: Loading) {
        dataBinding?.loadingComponent?.startService(loading = loading) {
            themeViewModel.service(idService = loading.idService)
        }
    }

    override fun startService() {
        themeViewModel.button.invoke(ThemeViewModel.THEME)
    }

    override fun themeArrayList(themeArrayList: ArrayList<Theme>) {
        dataBinding?.themeVerticalList?.setAdapter(arrayList = themeArrayList)
    }

    override fun theme(theme: Theme) {
        findNavController().navigate(R.id.sub_theme_fragment, Bundle().apply {
            putParcelable(Setting.THEME_BUNDLE, theme)
        })
    }
}