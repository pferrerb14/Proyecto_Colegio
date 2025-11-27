package com.leandro1995.seito.activity

import androidx.activity.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.databinding.ActivityExamAddBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.intent.callback.action.ExamAddIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.ExamAddIntentEventCallBack
import com.leandro1995.seito.intent.config.action.ExamAddIntentActionConfig
import com.leandro1995.seito.intent.config.event.ExamAddIntentEventConfig
import com.leandro1995.seito.viewmodel.ExamAddViewModel

class ExamAddActivity : ActivityAmbient<ActivityExamAddBinding>(), ExamAddIntentActionCallBack,
    ExamAddIntentEventCallBack {

    private val examAddViewModel by viewModels<ExamAddViewModel>()
    private val examAddIntentEventConfig =
        ExamAddIntentEventConfig(examAddIntentEventCallBack = this)
    private val examAddIntentActionConfig =
        ExamAddIntentActionConfig(examAddIntentActionCallBack = this)

    override var idLayout: Int = R.layout.activity_exam_add

    override fun initView() {
        dataBinding?.examAddViewModel = examAddViewModel
    }

    override fun initEventToAction() {
        lifecycleScope {
            examAddViewModel.event.collect { examAddIntentEvent ->
                examAddIntentEventConfig.initConfig(event = examAddIntentEvent)
            }
        }

        lifecycleScope {
            examAddViewModel.action.collect { examAddIntentAction ->
                examAddIntentActionConfig.initConfig(event = examAddIntentAction)
            }
        }
    }

    override fun loading(loading: Loading) {

    }
}