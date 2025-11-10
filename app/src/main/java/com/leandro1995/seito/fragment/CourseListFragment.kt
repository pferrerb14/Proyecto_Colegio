package com.leandro1995.seito.fragment

import android.content.Intent
import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.CourseAddActivity
import com.leandro1995.seito.component.list.callback.CourseGridComponentListCallBack
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentCourseListBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.CourseListIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.CourseListIntentEventCallBack
import com.leandro1995.seito.intent.config.action.CourseListIntentActionConfig
import com.leandro1995.seito.intent.config.event.CourseListIntentEventConfig
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.viewmodel.CourseListViewModel

class CourseListFragment : FragmentAmbient<FragmentCourseListBinding>(),
    CourseListIntentActionCallBack, CourseListIntentEventCallBack, CourseGridComponentListCallBack {

    private val courseListViewModel by viewModels<CourseListViewModel>()
    private val courseListIntentActionConfig =
        CourseListIntentActionConfig(courseListIntentActionCallBack = this)
    private val courseListIntentEventConfig =
        CourseListIntentEventConfig(courseListIntentEventCallBack = this)

    override var idLayout: Int = R.layout.fragment_course_list

    override fun initView() {
        dataBinding?.apply {
            courseListViewModel = this@CourseListFragment.courseListViewModel
            appBarBlueInclude.toolbar.let {
                Toolbar(materialToolbar = it, idTitle = R.string.course_title).config()
            }
            courseGridComponentList.courseGridComponentListCallBack = this@CourseListFragment
        }
    }

    override fun initEventToAction() {
        lifecycleScope {
            courseListViewModel.event.collect { courseListIntentEvent ->
                courseListIntentEventConfig.initConfig(event = courseListIntentEvent)
            }
        }

        lifecycleScope {
            courseListViewModel.action.collect { courseListIntentAction ->
                courseListIntentActionConfig.initConfig(event = courseListIntentAction)
            }
        }
    }

    override fun loading(loading: Loading) {
        dataBinding?.loadingComponent?.startService(loading = loading) {
            courseListViewModel.service(idService = loading.idService)
        }
    }

    override fun startService() {
        courseListViewModel.button.invoke(CourseListViewModel.COURSE)
    }

    override fun courseArrayList(courseArrayList: ArrayList<Course>) {
        dataBinding?.courseGridComponentList?.setAdapter(arrayList = courseArrayList)
    }

    override fun course(course: Course) {
        startActivity(Intent(requireContext(), CourseAddActivity::class.java).apply {
            putExtra(Setting.COURSE_PUT_EXTRA, course)
        })
    }
}