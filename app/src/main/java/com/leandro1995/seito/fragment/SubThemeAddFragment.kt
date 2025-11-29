package com.leandro1995.seito.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.leandro1995.seito.R
import com.leandro1995.seito.component.list.config.callback.SubThemeVerticalComponentListCallBack
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
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.util.dialog.AppUtilDialog
import com.leandro1995.seito.viewmodel.SubThemeAddViewModel
import com.leandro1995.seito.viewmodel.SubThemeViewModel

class SubThemeAddFragment : FragmentAmbient<FragmentSubThemeAddBinding>(),
    SubThemeAddIntentActionCallBack, SubThemeAddIntentEventCallBack,
    SubThemeVerticalComponentListCallBack {

    private val subThemeViewModel by viewModels<SubThemeAddViewModel>()
    private val subThemeAddIntentActionConfig =
        SubThemeAddIntentActionConfig(subThemeAddIntentActionCallBack = this)
    private val subThemeAddIntentEventConfig =
        SubThemeAddIntentEventConfig(subThemeAddIntentEventCallBack = this)

    override var idLayout: Int = R.layout.fragment_sub_theme_add

    override fun initView() {
        dataBinding?.apply {
            subThemeViewModel = this@SubThemeAddFragment.subThemeViewModel
            subThemeVerticalComponentList.subThemeVerticalComponentListCallBack =
                this@SubThemeAddFragment
        }
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
        dataBinding?.subThemeAddFloatingActionButton?.visibility = View.GONE
        dataBinding?.loadingComponent?.startService(loading = loading) {
            subThemeViewModel.service(idService = loading.idService)
        }
    }

    override fun startService() {
        subThemeViewModel.button.invoke(SubThemeViewModel.SUB_THEME)
    }

    override fun subThemeList(subThemeArrayList: ArrayList<SubTheme>) {
        dataBinding?.subThemeAddFloatingActionButton?.post {
            dataBinding?.subThemeAddFloatingActionButton?.visibility = View.VISIBLE
        }
        dataBinding?.subThemeVerticalComponentList?.setAdapter(arrayList = subThemeArrayList)
    }

    override fun topicEditorBottomSheet() {
        AppUtilDialog.topicEditorBottomSheet(fragmentManager = parentFragmentManager) { name ->
            subThemeViewModel.themeName = name
            subThemeViewModel.button.invoke(SubThemeAddViewModel.NAME_SUB_THEME_VALIDATION)
        }
    }

    override fun message(alertMessage: AlertMessage) {
        AppUtilDialog.dialogMaterialDesign(context = requireContext(), alertMessage = alertMessage)
    }

    override fun subTheme(subTheme: SubTheme) {
        findNavController().navigate(R.id.sub_theme_level_add_fragment, Bundle().apply {
            putString(Setting.ID_COURSE_BUNDLE, subThemeViewModel.idCourse)
            putString(Setting.ID_THEME_BUNDLE, subThemeViewModel.theme.id)
            putParcelable(Setting.ID_SUB_THEME_BUNDLE, subTheme)
        })
    }
}