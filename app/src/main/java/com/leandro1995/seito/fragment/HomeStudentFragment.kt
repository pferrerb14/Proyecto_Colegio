package com.leandro1995.seito.fragment

import android.content.Intent
import android.view.View
import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ThemeActivity
import com.leandro1995.seito.activity.VideoDetailActivity
import com.leandro1995.seito.background.coroutine.BackGroundCoroutine
import com.leandro1995.seito.component.list.callback.CourseGridComponentListCallBack
import com.leandro1995.seito.component.list.callback.VideoGridComponentListCallBack
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentHomeStudentBinding
import com.leandro1995.seito.extension.capsSentences
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.extension.visible
import com.leandro1995.seito.extension.youtubeStartActivity
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.HomeStudentIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.HomeStudentIntentEventCallBack
import com.leandro1995.seito.intent.config.action.HomeStudentIntentActionConfig
import com.leandro1995.seito.intent.config.event.HomeIntentEventConfig
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.protodatastore.config.UserProtoDataStoreConfig
import com.leandro1995.seito.viewmodel.HomeStudentViewModel

class HomeStudentFragment : FragmentAmbient<FragmentHomeStudentBinding>(),
    HomeStudentIntentActionCallBack, HomeStudentIntentEventCallBack {

    private val homeStudentViewModel by viewModels<HomeStudentViewModel>()

    private val homeStudentIntentActionConfig =
        HomeStudentIntentActionConfig(homeStudentIntentActionCallBack = this)

    private val homeStudentIntentEventConfig =
        HomeIntentEventConfig(homeStudentIntentEventCallBack = this)

    private val backGroundCoroutine = BackGroundCoroutine()

    override var idLayout: Int = R.layout.fragment_home_student

    override fun initView() {
        dataBinding?.homeStudentViewmodel = homeStudentViewModel
    }

    override fun initEventToAction() {

        lifecycleScope {
            homeStudentViewModel.event.collect { homeIntentEvent ->
                homeStudentIntentEventConfig.initConfig(event = homeIntentEvent)
            }
        }

        lifecycleScope {
            homeStudentViewModel.action.collect { homeIntentAction ->
                homeStudentIntentActionConfig.initConfig(event = homeIntentAction)
            }
        }
    }

    override fun loading(loading: Loading) {
        dataBinding?.loadingComponent?.startService(loading = loading) {
            homeStudentViewModel.service(idService = loading.idService)
        }
    }

    override fun getProtoDataStore() {
        backGroundCoroutine.start {
            UserProtoDataStoreConfig.apply {
                homeStudentViewModel.protoDataStore(
                    name = getName(), lastName = getLastName(), nameTeacher = getNameTeacher()
                )
            }

            homeStudentViewModel.button.invoke(HomeStudentViewModel.GET_PROTO_DATA_STORE)
        }
    }

    override fun studentDetail(student: Student) {
        dataBinding?.apply {
            initialComponent.setText(text = student.fullName())
            studentNameText.text =
                getString(R.string.student_name_text, student.fullName().capsSentences())
            studentNameDetailText.text = student.fullName().capsSentences()
            teacherNameDetailText.text =
                getString(R.string.name_teacher_text, student.teacher.fullName().capsSentences())
        }

        homeStudentViewModel.button.invoke(HomeStudentViewModel.COURSE_LIST)
    }

    override fun courseVideoArrayList(courseArrayList: ArrayList<Course>) {
        dataBinding?.headerVideoLinear?.visibility = View.VISIBLE
        dataBinding?.watchFullVideoText?.visibility = visible(isVisible = courseArrayList.isEmpty())

        dataBinding?.videoGridList?.apply {
            visibility = View.VISIBLE
            setAdapter(arrayList = courseArrayList)
            videoGridComponentListCallBack = object : VideoGridComponentListCallBack {
                override fun videoUrl(videoUrl: String) {
                    requireActivity().youtubeStartActivity(url = videoUrl)
                }
            }
        }
    }

    override fun courseArrayList(courseArrayList: ArrayList<Course>) {
        dataBinding?.headerCourseText?.visibility = View.VISIBLE

        dataBinding?.courseGridList?.apply {
            visibility = View.VISIBLE
            setAdapter(arrayList = courseArrayList)
            courseGridComponentListCallBack = object : CourseGridComponentListCallBack {
                override fun course(course: Course) {
                    startActivity(Intent(requireContext(), ThemeActivity::class.java).apply {
                        putExtra(Setting.COURSE_PUT_EXTRA, course)
                    })
                }
            }
        }
    }

    override fun videoDetail(courseArrayList: ArrayList<Course>) {
        startActivity(Intent(requireContext(), VideoDetailActivity::class.java).apply {
            putExtra(Setting.COURSE_ARRAY_LIST_PUT_EXTRA, courseArrayList)
        })
    }
}