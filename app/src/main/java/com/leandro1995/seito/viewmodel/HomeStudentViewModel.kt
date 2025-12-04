package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.HomeStudentIntentAction
import com.leandro1995.seito.intent.event.HomeStudentIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Exam
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class HomeStudentViewModel : ViewModelAmbient<HomeStudentIntentAction, HomeStudentIntentEvent>() {

    private val student = Student()
    var exam = Exam()
    private val courseVideoArrayList = arrayListOf<Course>()
    private val courseArrayList = arrayListOf<Course>()
    private val examArrayList = arrayListOf<Exam>()

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

            EXAM_SELECT -> {
                examSelect()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            COURSE_VIDEO_FIREBASE -> {
                courseVideoFirebase()
            }

            COURSE_FIREBASE -> {
                courseFirebase()
            }

            EXAM_FIREBASE -> {
                examFirebase()
            }

            EXAM_SELECT_FIREBASE -> {
                examSelectFirebase()
            }
        }
    }

    fun protoDataStore(name: String, lastName: String, nameTeacher: String) {
        student.name = name
        student.lastName = lastName
        student.teacher.name = nameTeacher
    }

    fun exam(exam: Exam) {
        this.exam = exam
        button.invoke(EXAM_SELECT)
    }

    private fun getProtoDataStore() {
        value(action = HomeStudentIntentAction(student = student))
    }

    private fun courseList() {
        loading(idService = COURSE_VIDEO_FIREBASE)
    }

    private fun videoDetail() {
        emit(event = HomeStudentIntentEvent.VideoDetail(courseArrayList = courseVideoArrayList))
    }

    private fun courseVideoFirebase() {
        student.courseVideoFirebaseArrayList(success = { response ->
            courseVideoArrayList.clear()
            courseVideoArrayList.addAll(response)
            loading(idService = COURSE_FIREBASE, isDelayDisable = false)
        }, error = {
            errorFirebase()
        })
    }

    private fun courseFirebase() {
        student.courseFirebaseArrayList(success = { response ->
            courseArrayList.clear()
            courseArrayList.addAll(response)
            loading(idService = EXAM_FIREBASE, isDelayDisable = false)
        }, error = {
            errorFirebase()
        })
    }

    private fun examFirebase() {
        student.examFirebaseArrayList(success = { response ->
            examArrayList.clear()
            examArrayList.addAll(response)
            value(
                action = HomeStudentIntentAction(
                    courseVideoArrayList = courseVideoArrayList,
                    courseArrayList = courseArrayList,
                    examArrayList = examArrayList
                )
            )
            loading()
        }, error = {
            errorFirebase()
        })
    }

    private fun errorFirebase() {
        courseVideoArrayList.clear()
        courseArrayList.clear()
        examArrayList.clear()
        value(
            action = HomeStudentIntentAction(
                courseVideoArrayList = courseVideoArrayList, courseArrayList = courseArrayList
            )
        )
        loading()
    }

    private fun examSelect() {
        loading(idService = EXAM_SELECT_FIREBASE)
    }

    private fun examSelectFirebase() {
        exam.questionFirebaseArrayList(success = {
            emit(event = HomeStudentIntentEvent.QuestionAnswer(questionArrayList = it))
            loading()
        }, error = {
            errorFirebase()
        })
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = HomeStudentIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    companion object {
        const val GET_PROTO_DATA_STORE = 0
        const val COURSE_LIST = 1
        const val VIDEO_DETAIL = 2
        const val EXAM_SELECT = 3
        private const val COURSE_VIDEO_FIREBASE = 4
        private const val COURSE_FIREBASE = 5
        private const val EXAM_FIREBASE = 6
        private const val EXAM_SELECT_FIREBASE = 7
    }
}