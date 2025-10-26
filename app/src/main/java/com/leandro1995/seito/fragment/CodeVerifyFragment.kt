package com.leandro1995.seito.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.leandro1995.seito.R
import com.leandro1995.seito.databinding.FragmentCodeVerifyBinding
import com.leandro1995.seito.fragment.ambient.FragmentAmbient

class CodeVerifyFragment : FragmentAmbient<FragmentCodeVerifyBinding>() {

    override var idLayout: Int = R.layout.fragment_code_verify
}