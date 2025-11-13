package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.intent.action.StudentListIntentAction
import com.leandro1995.seito.intent.event.StudentListIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class StudentListViewModel : ViewModelAmbient<StudentListIntentAction, StudentListIntentEvent>() {

    private val teacher = Teacher()
    private val studentArrayList = arrayListOf<Student>()

    override fun event(action: Int) {
        when (action) {
            USER_LIST -> {
                userList()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            USER_LIST_FIREBASE -> {
                userListFirebase()
            }
        }
    }

    private fun userList() {
        loading(idService = USER_LIST_FIREBASE)
    }

    private fun userListFirebase() {
        teacher.studentFirebaseArrayList(success = { response ->
            studentArrayList.clear()
            studentArrayList.addAll(response)
            value(action = StudentListIntentAction(studentArrayList = studentArrayList))
            loading()
        }, error = {
            studentArrayList.clear()
            loading()
        })
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = StudentListIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    loading = Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    companion object {
        const val USER_LIST = 0
        private const val USER_LIST_FIREBASE = 1
    }
}