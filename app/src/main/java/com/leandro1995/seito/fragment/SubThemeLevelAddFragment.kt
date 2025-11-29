package com.leandro1995.seito.fragment

import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.QuestionListActivity
import com.leandro1995.seito.component.list.config.callback.SubThemeLevelVerticalComponentListCallBack
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentSubThemeLevelAddBinding
import com.leandro1995.seito.extension.argumentParcelable
import com.leandro1995.seito.extension.argumentString
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.extension.visible
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.SubThemeLevelAddIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.SubThemeLevelAddIntentEventCallBack
import com.leandro1995.seito.intent.config.action.SubThemeLevelAddIntentActionConfig
import com.leandro1995.seito.intent.config.event.SubThemeLevelAddIntentEventConfig
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Level
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.util.dialog.AppUtilDialog
import com.leandro1995.seito.viewmodel.SubThemeLevelAddViewModel

class SubThemeLevelAddFragment : FragmentAmbient<FragmentSubThemeLevelAddBinding>(),
    SubThemeLevelAddIntentActionCallBack, SubThemeLevelAddIntentEventCallBack,
    SubThemeLevelVerticalComponentListCallBack {

    private val subThemeLevelAddViewModel by viewModels<SubThemeLevelAddViewModel>()
    private val subThemeLevelAddIntentActionConfig =
        SubThemeLevelAddIntentActionConfig(subThemeLevelAddIntentActionCallBack = this)
    private val subThemeLevelAddIntentEventConfig =
        SubThemeLevelAddIntentEventConfig(subThemeLevelAddIntentEventCallBack = this)

    override var idLayout: Int = R.layout.fragment_sub_theme_level_add

    override fun initView() {
        dataBinding?.apply {
            subThemeLevelAddViewModel = this@SubThemeLevelAddFragment.subThemeLevelAddViewModel
            subThemeLevelVerticalComponentList.subThemeLevelVerticalComponentListCallBack =
                this@SubThemeLevelAddFragment
            subThemeLevelAddViewModel?.levelStringArrayList =
                Setting.levelStringArrayList(context = requireContext())
        }
    }

    override fun initEventToAction() {
        lifecycleScope {
            subThemeLevelAddViewModel.event.collect { subThemeLevelAddIntentEvent ->
                subThemeLevelAddIntentEventConfig.initConfig(event = subThemeLevelAddIntentEvent)
            }
        }

        lifecycleScope {
            subThemeLevelAddViewModel.action.collect { subThemeLevelAddIntentAction ->
                subThemeLevelAddIntentActionConfig.initConfig(event = subThemeLevelAddIntentAction)
            }
        }
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

    override fun loading(loading: Loading) {
        dataBinding?.loadingComponent?.startService(loading = loading) {
            subThemeLevelAddViewModel.service(idService = loading.idService)
        }
    }

    override fun starService() {
        subThemeLevelAddViewModel.button.invoke(SubThemeLevelAddViewModel.LEVEL)
    }

    override fun levelArrayList(levelArrayList: ArrayList<Level>, isShowButton: Boolean) {
        dataBinding?.let {
            it.levelAddButton.visibility = visible(isVisible = isShowButton)
            it.subThemeLevelVerticalComponentList.setAdapter(arrayList = levelArrayList)
        }
    }

    override fun message(alertMessage: AlertMessage) {
        AppUtilDialog.dialogMaterialDesign(
            context = requireContext(), alertMessage = alertMessage
        ) {
            findNavController().popBackStack()
        }
    }

    override fun level(level: Level) {
        startActivity(Intent(requireContext(), QuestionListActivity::class.java).apply {
            putExtra(Setting.LEVEL_PUT_EXTRA, level)
        })
    }
}