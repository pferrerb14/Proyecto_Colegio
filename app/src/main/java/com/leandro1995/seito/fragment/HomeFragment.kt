package com.leandro1995.seito.fragment

import android.content.Intent
import android.view.View
import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ThemeActivity
import com.leandro1995.seito.activity.VideoDetailActivity
import com.leandro1995.seito.background.coroutine.BackGroundCoroutine
import com.leandro1995.seito.component.list.callback.CourseGridComponentListCallBack
import com.leandro1995.seito.component.list.callback.VideoGridListCallBack
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentHomeBinding
import com.leandro1995.seito.extension.capsSentences
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.extension.visible
import com.leandro1995.seito.extension.youtubeStartActivity
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.HomeIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.HomeIntentEventCallBack
import com.leandro1995.seito.intent.config.action.HomeIntentActionConfig
import com.leandro1995.seito.intent.config.event.HomeIntentEventConfig
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.protodatastore.config.UserProtoDataStoreConfig
import com.leandro1995.seito.viewmodel.HomeViewModel

class HomeFragment : FragmentAmbient<FragmentHomeBinding>(), HomeIntentActionCallBack,
    HomeIntentEventCallBack {

    private val homeViewModel by viewModels<HomeViewModel>()

    private val homeIntentActionConfig = HomeIntentActionConfig(homeIntentActionCallBack = this)

    private val homeIntentEventConfig = HomeIntentEventConfig(homeIntentEventCallBack = this)

    private val backGroundCoroutine = BackGroundCoroutine()


    override var idLayout: Int = R.layout.fragment_home

    override fun initView() {
        dataBinding?.homeViewmodel = homeViewModel
    }

    override fun initEventToAction() {

        lifecycleScope {
            homeViewModel.event.collect { homeIntentEvent ->
                homeIntentEventConfig.initConfig(event = homeIntentEvent)
            }
        }

        lifecycleScope {
            homeViewModel.action.collect { homeIntentAction ->
                homeIntentActionConfig.initConfig(event = homeIntentAction)
            }
        }
    }

    override fun loading(loading: Loading) {
        dataBinding?.loadingComponent?.startService(loading = loading) {
            homeViewModel.service(idService = loading.idService)
        }
    }

    override fun getProtoDataStore() {
        backGroundCoroutine.start {
            UserProtoDataStoreConfig.apply {
                homeViewModel.protoDataStore(
                    name = getName(), lastName = getLastName(), nameTeacher = getNameTeacher()
                )
            }

            homeViewModel.button.invoke(HomeViewModel.GET_PROTO_DATA_STORE)
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

        homeViewModel.button.invoke(HomeViewModel.COURSE_LIST)
    }

    override fun courseVideoArrayList(courseArrayList: ArrayList<Course>) {
        dataBinding?.headerVideoLinear?.visibility = View.VISIBLE
        dataBinding?.watchFullVideoText?.visibility = visible(isVisible = courseArrayList.isEmpty())

        dataBinding?.videoGridList?.apply {
            visibility = View.VISIBLE
            setAdapter(arrayList = courseArrayList)
            videoGridListCallBack = object : VideoGridListCallBack {
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
                override fun themeArrayList(themeArrayList: ArrayList<Theme>) {
                    startActivity(Intent(requireContext(), ThemeActivity::class.java).apply {
                        putExtra(Setting.THEME_ARRAY_LIST_PUT_EXTRA, themeArrayList)
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