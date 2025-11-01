package com.leandro1995.seito.activity

import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.databinding.ActivityStudentRegisterBinding
import com.leandro1995.seito.model.design.Toolbar

class StudentRegisterActivity : ActivityAmbient<ActivityStudentRegisterBinding>() {

    override var idLayout: Int = R.layout.activity_student_register

    override var isStatusBarColorIcon: Boolean = true

    override fun initView() {
        dataBinding?.appBarInclude?.toolbar?.let {
            Toolbar(
                context = this,
                materialToolbar = it,
                idTitle = R.string.register_student_title,
                isArrow = true
            ).config {
                finish()
            }
        }
    }
}