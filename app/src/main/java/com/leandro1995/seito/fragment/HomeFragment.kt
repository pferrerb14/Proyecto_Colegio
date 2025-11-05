package com.leandro1995.seito.fragment

import android.view.View
import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.background.coroutine.BackGroundCoroutine
import com.leandro1995.seito.component.list.callback.VideoGridListCallBack
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.databinding.FragmentHomeBinding
import com.leandro1995.seito.extension.capsSentences
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.extension.youtubeStartActivity
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.HomeIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.HomeIntentEventCallBack
import com.leandro1995.seito.intent.config.action.HomeIntentActionConfig
import com.leandro1995.seito.intent.config.event.HomeIntentEventConfig
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Student
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
            teacherNameDetailText.text = student.teacher.fullName().capsSentences()
        }

        homeViewModel.button.invoke(HomeViewModel.COURSE_LIST)
    }

    override fun courseArrayList(courseArrayList: ArrayList<Course>) {
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
}