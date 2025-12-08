package com.leandro1995.seito.fragment

import com.leandro1995.seito.R
import com.leandro1995.seito.databinding.FragmentAssistantBinding
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.model.design.Toolbar

class AssistantFragment : FragmentAmbient<FragmentAssistantBinding>() {

    override var idLayout: Int = R.layout.fragment_assistant

    override fun initView() {
        dataBinding?.appBarBlueInclude?.toolbar?.let {
            Toolbar(materialToolbar = it, idTitle = R.string.assistant_title).config()
        }
    }
}