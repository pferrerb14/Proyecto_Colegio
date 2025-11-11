package com.leandro1995.seito.fragment

import android.view.View
import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentThemeAddBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.extension.parcelable
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.ThemeAddIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.ThemeAddIntentEventCallBack
import com.leandro1995.seito.intent.config.action.ThemeAddIntentActionConfig
import com.leandro1995.seito.intent.config.event.ThemeAddIntentEventConfig
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.util.dialog.AppUtilDialog
import com.leandro1995.seito.viewmodel.ThemeAddViewModel

class ThemeAddFragment : FragmentAmbient<FragmentThemeAddBinding>(), ThemeAddIntentActionCallBack,
    ThemeAddIntentEventCallBack {

    private val themeAddViewModel by viewModels<ThemeAddViewModel>()
    private val themeAddIntentActionConfig =
        ThemeAddIntentActionConfig(themeAddIntentActionCallBack = this)
    private val themeAddIntentEventConfig =
        ThemeAddIntentEventConfig(themeAddIntentEventCallBack = this)

    override var idLayout: Int = R.layout.fragment_theme_add

    override fun initView() {
        dataBinding?.themeAddViewModel = themeAddViewModel
    }

    override fun initEventToAction() {
        lifecycleScope {
            themeAddViewModel.event.collect { themeAddIntentEvent ->
                themeAddIntentEventConfig.initConfig(event = themeAddIntentEvent)
            }
        }

        lifecycleScope {
            themeAddViewModel.action.collect { themeAddIntentAction ->
                themeAddIntentActionConfig.initConfig(event = themeAddIntentAction)
            }
        }
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

    override fun loading(loading: Loading) {
        dataBinding?.themeAddFloatingActionButton?.visibility = View.GONE
        dataBinding?.loadingComponent?.startService(loading = loading) {
            themeAddViewModel.service(idService = loading.idService)
        }
    }

    override fun startService() {
        themeAddViewModel.button.invoke(ThemeAddViewModel.THEME_ADD)
    }

    override fun themeArrayList(themeArrayList: ArrayList<Theme>) {
        dataBinding?.themeAddFloatingActionButton?.post {
            dataBinding?.themeAddFloatingActionButton?.visibility = View.VISIBLE
        }
        dataBinding?.themeVerticalList?.setAdapter(arrayList = themeArrayList)
    }

    override fun topicEditorBottomSheet() {
        AppUtilDialog.topicEditorBottomSheet(fragmentManager = parentFragmentManager)
    }
}