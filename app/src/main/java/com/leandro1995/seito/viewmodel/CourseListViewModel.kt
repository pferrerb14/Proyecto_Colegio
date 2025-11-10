package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.CourseListIntentAction
import com.leandro1995.seito.intent.event.CourseListIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class CourseListViewModel : ViewModelAmbient<CourseListIntentAction, CourseListIntentEvent>() {

    private val teacher = Teacher()
    private val courseArrayList = arrayListOf<Course>()

    override fun event(action: Int) {
        when (action) {
            COURSE -> {
                course()
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

    private fun course() {
        loading(idService = COURSE_FIREBASE)
    }

    private fun courseFirebase() {
        teacher.courseFirebaseArrayList(success = { response ->
            courseArrayList.clear()
            courseArrayList.addAll(response)
            value(action = CourseListIntentAction(courseArrayList = courseArrayList))
            loading()
        }, error = {
            courseArrayList.clear()
            value(action = CourseListIntentAction())
            loading()
        })
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = CourseListIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    companion object {
        const val COURSE = 0
        const val COURSE_FIREBASE = 1
    }
}