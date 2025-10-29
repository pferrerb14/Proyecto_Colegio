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
import com.leandro1995.seito.util.design.StudentRegisterUtilDesign
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

        studentRegisterViewModel.student.sex = getString(R.string.male_constant)
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

    override fun maleSelect() {
        dataBinding?.let {
            StudentRegisterUtilDesign.sexSelect(
                context = requireContext(),
                activeTriple = Triple(it.maleView, it.maleImage, it.maleLinear),
                deactivateTriple = Triple(it.femaleView, it.femaleImage, it.femaleLinear),
                activeIcon = R.drawable.ic_male,
                deactivateIcon = R.drawable.ic_female_hint
            )
        }
        studentRegisterViewModel.student.sex = getString(R.string.male_constant)
    }

    override fun femaleSelect() {
        dataBinding?.let {
            StudentRegisterUtilDesign.sexSelect(
                context = requireContext(),
                activeTriple = Triple(it.femaleView, it.femaleImage, it.femaleLinear),
                deactivateTriple = Triple(it.maleView, it.maleImage, it.maleLinear),
                activeIcon = R.drawable.ic_female,
                deactivateIcon = R.drawable.ic_male_hint
            )
        }
        studentRegisterViewModel.student.sex = getString(R.string.female_constant)
    }
}