package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.databinding.FragmentStudentRegisterBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.StudentRegisterIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.StudentRegisterIntentEventCallBack
import com.leandro1995.seito.intent.config.action.StudentRegisterIntentActionConfig
import com.leandro1995.seito.intent.config.event.StudentRegisterIntentEventConfig
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.util.dialog.AppUtilDialog
import com.leandro1995.seito.viewmodel.StudentRegisterViewModel

class StudentRegisterFragment : FragmentAmbient<FragmentStudentRegisterBinding>(),
    StudentRegisterIntentActionCallBack, StudentRegisterIntentEventCallBack {

    private val studentRegisterViewModel by viewModels<StudentRegisterViewModel>()
    private val studentRegisterIntentActionConfig =
        StudentRegisterIntentActionConfig(studentRegisterIntentActionCallBack = this)
    private val studentRegisterIntentEventConfig =
        StudentRegisterIntentEventConfig(studentRegisterIntentEventCallBack = this)

    override var idLayout: Int = R.layout.fragment_student_register

    override fun initView() {
        dataBinding?.studentRegisterViewModel = studentRegisterViewModel
    }

    override fun initEventToAction() {
        lifecycleScope {
            studentRegisterViewModel.event.collect { studentRegisterIntentEvent ->
                studentRegisterIntentEventConfig.initConfig(event = studentRegisterIntentEvent)
            }
        }

        lifecycleScope {
            studentRegisterViewModel.action.collect { studentRegisterIntentAction ->
                studentRegisterIntentActionConfig.initConfig(event = studentRegisterIntentAction)
            }
        }
    }

    override fun alertMessage(alertMessage: AlertMessage) {
        AppUtilDialog.dialogMaterialDesign(context = requireContext(), alertMessage = alertMessage)
    }
}