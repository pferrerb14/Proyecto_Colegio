package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.databinding.FragmentStudentRegisterBinding
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.viewmodel.StudentRegisterViewModel

class StudentRegisterFragment : FragmentAmbient<FragmentStudentRegisterBinding>() {

    private val studentRegisterViewModel by viewModels<StudentRegisterViewModel>()

    override var idLayout: Int = R.layout.fragment_student_register

    override fun initView() {
        dataBinding?.studentRegisterViewModel = studentRegisterViewModel
    }

    override fun initEventToAction() {

    }
}