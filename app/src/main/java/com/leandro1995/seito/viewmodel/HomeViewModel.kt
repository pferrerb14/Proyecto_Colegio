package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.HomeIntentAction
import com.leandro1995.seito.intent.event.HomeIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class HomeViewModel : ViewModelAmbient<HomeIntentAction, HomeIntentEvent>() {

    private val student = Student()
    private val courseVideoArrayList = arrayListOf<Course>()

    override fun event(action: Int) {
        when (action) {
            GET_PROTO_DATA_STORE -> {
                getProtoDataStore()
            }

            COURSE_LIST -> {
                courseList()
            }

            VIDEO_DETAIL -> {
                videoDetail()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            COURSE_FIREBASE -> {
                courseFirebase()
            }
        }
    }

    fun protoDataStore(name: String, lastName: String, nameTeacher: String) {
        student.name = name
        student.lastName = lastName
        student.teacher.name = nameTeacher
    }

    private fun getProtoDataStore() {
        value(action = HomeIntentAction(student = student))
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = HomeIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    private fun courseList() {
        loading(idService = COURSE_FIREBASE)
    }

    private fun videoDetail() {
        emit(event = HomeIntentEvent.VideoDetail(courseArrayList = courseVideoArrayList))
    }

    private fun courseFirebase() {
        student.courseVideoFirebaseArrayList(success = { response ->
            courseVideoArrayList.clear()
            courseVideoArrayList.addAll(response)
            value(action = HomeIntentAction(courseVideoArrayList = courseVideoArrayList))
            loading()
        }, error = {

        })
    }

    companion object {
        const val GET_PROTO_DATA_STORE = 0
        const val COURSE_LIST = 1
        const val VIDEO_DETAIL = 2
        const val COURSE_FIREBASE = 3
    }
}