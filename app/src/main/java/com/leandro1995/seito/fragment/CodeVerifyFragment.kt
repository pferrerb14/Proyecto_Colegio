package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.databinding.FragmentCodeVerifyBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.event.CodeVerifyIntentEventCallBack
import com.leandro1995.seito.intent.config.event.CodeVerifyIntentEventConfig
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.util.dialog.AppUtilDialog
import com.leandro1995.seito.viewmodel.CodeVerifyViewModel

class CodeVerifyFragment : FragmentAmbient<FragmentCodeVerifyBinding>(),
    CodeVerifyIntentEventCallBack {
    private val codeVerifyViewModel by viewModels<CodeVerifyViewModel>()

    private val codeVerifyIntentEventConfig =
        CodeVerifyIntentEventConfig(codeVerifyIntentEventCallBack = this)

    override var idLayout: Int = R.layout.fragment_code_verify

    override fun initView() {
        dataBinding?.codeVerifyViewModel = codeVerifyViewModel
    }

    override fun initEventToAction() {
        lifecycleScope {
            codeVerifyViewModel.event.collect { codeVerifyIntentEvent ->
                codeVerifyIntentEventConfig.initConfig(event = codeVerifyIntentEvent)
            }
        }
    }

    override fun alertMessage(alertMessage: AlertMessage) {
        AppUtilDialog.dialogMaterialDesign(context = requireContext(), alertMessage = alertMessage)
    }
}