package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.databinding.FragmentAssistantBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.AssistantIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.AssistantIntentEventCallBack
import com.leandro1995.seito.intent.config.action.AssistantIntentActionConfig
import com.leandro1995.seito.intent.config.event.AssistantIntentEventConfig
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.viewmodel.AssistantViewModel

class AssistantFragment : FragmentAmbient<FragmentAssistantBinding>(), AssistantIntentEventCallBack,
    AssistantIntentActionCallBack {

    private val assistantViewModel by viewModels<AssistantViewModel>()
    private val assistantIntentEventConfig =
        AssistantIntentEventConfig(assistantIntentEventCallBack = this)
    private val assistantIntentActionConfig =
        AssistantIntentActionConfig(assistantIntentActionCallBack = this)

    override var idLayout: Int = R.layout.fragment_assistant

    override fun initView() {
        dataBinding?.apply {
            assistantViewModel = this@AssistantFragment.assistantViewModel

            appBarBlueInclude.toolbar.let {
                Toolbar(materialToolbar = it, idTitle = R.string.assistant_title).config()
            }
        }
    }

    override fun initEventToAction() {
        lifecycleScope {
            assistantViewModel.event.collect { assistantIntentEvent ->
                assistantIntentEventConfig.initConfig(event = assistantIntentEvent)
            }
        }

        lifecycleScope {
            assistantViewModel.action.collect { assistantIntentAction ->
                assistantIntentActionConfig.initConfig(event = assistantIntentAction)
            }
        }
    }

    override fun loading(loading: Loading) {

    }
}