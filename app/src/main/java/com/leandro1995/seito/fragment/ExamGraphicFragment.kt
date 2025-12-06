package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.databinding.FragmentExamGraphicBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.ExamGraphicIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.ExamGraphicIntentEventCallBack
import com.leandro1995.seito.intent.config.action.ExamGraphicIntentActionConfig
import com.leandro1995.seito.intent.config.event.ExamGraphicIntentEventConfig
import com.leandro1995.seito.viewmodel.ExamGraphicViewModel

class ExamGraphicFragment : FragmentAmbient<FragmentExamGraphicBinding>(),
    ExamGraphicIntentEventCallBack, ExamGraphicIntentActionCallBack {

    private val examGraphicViewModel by viewModels<ExamGraphicViewModel>()

    private val examGraphicIntentEventConfig =
        ExamGraphicIntentEventConfig(examGraphicIntentEventCallBack = this)
    private val examGraphicIntentActionConfig =
        ExamGraphicIntentActionConfig(examGraphicIntentEventCallBack = this)

    override var idLayout: Int = R.layout.fragment_exam_graphic

    override fun initView() {
        dataBinding?.examGraphicViewModel = examGraphicViewModel
    }

    override fun initEventToAction() {
        lifecycleScope {
            examGraphicViewModel.event.collect { examGraphicIntentEvent ->
                examGraphicIntentEventConfig.initConfig(event = examGraphicIntentEvent)
            }
        }

        lifecycleScope {
            examGraphicViewModel.action.collect { examGraphicIntentAction ->
                examGraphicIntentActionConfig.initConfig(event = examGraphicIntentAction)
            }
        }
    }

    override fun loading(loading: Loading) {

    }
}