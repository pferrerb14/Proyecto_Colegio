package com.leandro1995.seito.fragment

import android.annotation.SuppressLint
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.leandro1995.seito.R
import com.leandro1995.seito.adapter.AssistantAdapter
import com.leandro1995.seito.background.coroutine.BackGroundCoroutine
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentAssistantBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.AssistantIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.AssistantIntentEventCallBack
import com.leandro1995.seito.intent.config.action.AssistantIntentActionConfig
import com.leandro1995.seito.intent.config.event.AssistantIntentEventConfig
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Chat
import com.leandro1995.seito.protodatastore.config.UserProtoDataStoreConfig
import com.leandro1995.seito.util.design.AssistantUtilDesign
import com.leandro1995.seito.viewmodel.AssistantViewModel

class AssistantFragment : FragmentAmbient<FragmentAssistantBinding>(), AssistantIntentEventCallBack,
    AssistantIntentActionCallBack {

    private val assistantViewModel by viewModels<AssistantViewModel>()
    private val assistantIntentEventConfig =
        AssistantIntentEventConfig(assistantIntentEventCallBack = this)
    private val assistantIntentActionConfig =
        AssistantIntentActionConfig(assistantIntentActionCallBack = this)

    private val backGroundCoroutine = BackGroundCoroutine()
    private val chatArrayList = arrayListOf<Chat>()

    private var assistantAdapter: AssistantAdapter? = null

    override var idLayout: Int = R.layout.fragment_assistant

    override fun initView() {
        dataBinding?.apply {
            assistantViewModel = this@AssistantFragment.assistantViewModel

            appBarBlueInclude.toolbar.let {
                Toolbar(materialToolbar = it, idTitle = R.string.assistant_title).config()
            }
        }
        assistantAdapter()
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

    override fun startList() {
        backGroundCoroutine.start {
            assistantViewModel.student.apply {
                name = UserProtoDataStoreConfig.getName()
                lastName = UserProtoDataStoreConfig.getLastName()
            }

            assistantViewModel.button.invoke(AssistantViewModel.ITEM_ONE_ADD)
        }
    }

    override fun fullName(fullName: String) {
        assistantViewModel.addItem(
            message = getString(R.string.assistant_text, fullName), type = Setting.ANSWER_CHAT
        )
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun chatArrayList(chatArrayList: ArrayList<Chat>) {
        dataBinding?.chatEditText?.setText("")
        this.chatArrayList.clear()
        this.chatArrayList.addAll(chatArrayList)

        assistantAdapter?.notifyDataSetChanged()

        AssistantUtilDesign.insertLastChatItem(
            recyclerView = dataBinding?.assistantRecyclerView,
            itemCount = assistantAdapter?.itemCount
        )
    }

    private fun assistantAdapter() {
        assistantAdapter = AssistantAdapter(chatArrayList = chatArrayList)

        dataBinding?.assistantRecyclerView?.apply {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = assistantAdapter
        }
    }
}