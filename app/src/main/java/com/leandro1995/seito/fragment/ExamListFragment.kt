package com.leandro1995.seito.fragment

import android.content.Intent
import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ExamAddActivity
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.databinding.FragmentExamListBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.ExamListIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.ExamListIntentEventCallBack
import com.leandro1995.seito.intent.config.action.ExamListIntentActionConfig
import com.leandro1995.seito.intent.config.event.ExamListIntentEventConfig
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.viewmodel.ExamListViewModel

class ExamListFragment : FragmentAmbient<FragmentExamListBinding>(), ExamListIntentActionCallBack,
    ExamListIntentEventCallBack {

    private val examListViewModel by viewModels<ExamListViewModel>()
    private val examListIntentEventConfig =
        ExamListIntentEventConfig(examListIntentEventCallBack = this)
    private val examListIntentActionConfig =
        ExamListIntentActionConfig(examListIntentActionCallBack = this)

    override var idLayout: Int = R.layout.fragment_exam_list

    override fun initView() {
        dataBinding?.apply {
            examListViewModel = this@ExamListFragment.examListViewModel
            Toolbar(
                materialToolbar = appBarBlueInclude.toolbar,
                idTitle = R.string.list_exam_title,
                icArrow = R.drawable.ic_arrow_white
            ).config()
        }
    }

    override fun initEventToAction() {
        lifecycleScope {
            examListViewModel.event.collect { examListIntentEvent ->
                examListIntentEventConfig.initConfig(event = examListIntentEvent)
            }
        }

        lifecycleScope {
            examListViewModel.action.collect { examListIntentAction ->
                examListIntentActionConfig.initConfig(event = examListIntentAction)
            }
        }
    }

    override fun loading(loading: Loading) {

    }

    override fun examAdd() {
        startActivity(Intent(requireContext(), ExamAddActivity::class.java))
    }
}