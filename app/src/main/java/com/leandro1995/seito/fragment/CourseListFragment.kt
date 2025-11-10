package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.databinding.FragmentCourseListBinding
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.viewmodel.CourseListViewModel

class CourseListFragment : FragmentAmbient<FragmentCourseListBinding>() {

    private val courseListViewModel by viewModels<CourseListViewModel>()

    override var idLayout: Int = R.layout.fragment_course_list

    override fun initView() {
        dataBinding?.courseListViewModel = courseListViewModel
    }
}