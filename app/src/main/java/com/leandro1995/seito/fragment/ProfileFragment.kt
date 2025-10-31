package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.databinding.FragmentProfileBinding
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.viewmodel.ProfileViewModel

class ProfileFragment : FragmentAmbient<FragmentProfileBinding>() {

    private val profileViewModel by viewModels<ProfileViewModel>()

    override var idLayout: Int = R.layout.fragment_profile

    override fun initView() {
        dataBinding?.profileViewModel = profileViewModel
    }
}