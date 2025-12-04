package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.component.list.config.callback.StudentVerticalComponentListCallBack
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.databinding.FragmentStudentListBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.StudentListIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.StudentListIntentEventCallBack
import com.leandro1995.seito.intent.config.action.StudentListIntentActionConfig
import com.leandro1995.seito.intent.config.event.StudentListIntentEventConfig
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.viewmodel.StudentListViewModel

class StudentListFragment : FragmentAmbient<FragmentStudentListBinding>(),
    StudentListIntentActionCallBack, StudentListIntentEventCallBack,
    StudentVerticalComponentListCallBack {

    private val studentListViewModel by viewModels<StudentListViewModel>()
    private val studentListIntentActionConfig =
        StudentListIntentActionConfig(studentListIntentActionCallBack = this)
    private val studentListIntentEventConfig =
        StudentListIntentEventConfig(studentListIntentEventCallBack = this)

    override var idLayout: Int = R.layout.fragment_student_list

    override fun initView() {
        dataBinding?.apply {
            studentListViewModel = this@StudentListFragment.studentListViewModel

            Toolbar(
                materialToolbar = appBarBlueInclude.toolbar, idTitle = R.string.student_title
            ).config()

            studentVerticalComponentList.studentVerticalComponentListCallBack =
                this@StudentListFragment
        }
    }

    override fun initEventToAction() {
        lifecycleScope {
            studentListViewModel.event.collect { studentListIntentEvent ->
                studentListIntentEventConfig.initConfig(event = studentListIntentEvent)
            }
        }

        lifecycleScope {
            studentListViewModel.action.collect { studentListIntentAction ->
                studentListIntentActionConfig.initConfig(event = studentListIntentAction)
            }
        }
    }

    override fun loading(loading: Loading) {
        dataBinding?.loadingComponent?.startService(loading = loading) {
            studentListViewModel.service(idService = loading.idService)
        }
    }

    override fun startService() {
        studentListViewModel.button.invoke(StudentListViewModel.USER_LIST)
    }

    override fun studentArrayList(studentArrayList: ArrayList<Student>) {
        dataBinding?.studentVerticalComponentList?.setAdapter(arrayList = studentArrayList)
    }

    override fun student(student: Student) {

    }
}