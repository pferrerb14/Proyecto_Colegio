package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.databinding.FragmentCodeVerifyBinding
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.viewmodel.CodeVerifyViewModel

class CodeVerifyFragment : FragmentAmbient<FragmentCodeVerifyBinding>() {

    private val codeVerifyViewModel by viewModels<CodeVerifyViewModel>()

    override var idLayout: Int = R.layout.fragment_code_verify

    override fun initView() {
        dataBinding?.codeVerifyViewModel = codeVerifyViewModel
    }
}