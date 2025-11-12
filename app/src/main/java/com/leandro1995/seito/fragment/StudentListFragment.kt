package com.leandro1995.seito.fragment

import com.leandro1995.seito.R
import com.leandro1995.seito.databinding.FragmentStudentListBinding
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.model.design.Toolbar

class StudentListFragment : FragmentAmbient<FragmentStudentListBinding>() {

    override var idLayout: Int = R.layout.fragment_student_list

    override fun initView() {
        dataBinding?.appBarBlueInclude?.toolbar?.let {
            Toolbar(materialToolbar = it, idTitle = R.string.student_title).config()
        }
    }
}