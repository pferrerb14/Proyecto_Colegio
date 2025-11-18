package com.leandro1995.seito.activity

import android.content.Intent
import androidx.activity.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.ActivityQuestionListBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.extension.parcelable
import com.leandro1995.seito.intent.callback.action.QuestionListIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.QuestionListIntentEventCallBack
import com.leandro1995.seito.intent.config.action.QuestionListIntentActionConfig
import com.leandro1995.seito.intent.config.event.QuestionListIntentEventConfig
import com.leandro1995.seito.model.entity.Level
import com.leandro1995.seito.viewmodel.QuestionListViewModel

class QuestionListActivity : ActivityAmbient<ActivityQuestionListBinding>(),
    QuestionListIntentActionCallBack, QuestionListIntentEventCallBack {

    val questionListViewModel by viewModels<QuestionListViewModel>()
    private val questionListIntentActionConfig =
        QuestionListIntentActionConfig(questionListIntentActionCallBack = this)
    private val questionListIntentEventConfig =
        QuestionListIntentEventConfig(questionListIntentEventCallBack = this)

    override var idLayout: Int = R.layout.activity_question_list

    override fun initView() {
        dataBinding?.questionListViewModel = questionListViewModel
    }

    override fun initEventToAction() {
        lifecycleScope {
            questionListViewModel.event.collect { questionListIntentEvent ->
                questionListIntentEventConfig.initConfig(event = questionListIntentEvent)
            }
        }

        lifecycleScope {
            questionListViewModel.action.collect { questionListIntentAction ->
                questionListIntentActionConfig.initConfig(event = questionListIntentAction)
            }
        }
    }

    override fun putExtra() {
        Setting.LEVEL_PUT_EXTRA.parcelable<Level>(activity = this)?.let {
            questionListViewModel.level = it
        }
    }

    override fun loading(loading: Loading) {

    }

    override fun questionAdd() {
        startActivity(Intent(this, QuestionAddActivity::class.java))
    }
}