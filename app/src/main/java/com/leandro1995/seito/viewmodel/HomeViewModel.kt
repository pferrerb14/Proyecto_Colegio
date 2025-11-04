package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.HomeIntentAction
import com.leandro1995.seito.intent.event.HomeIntentEvent
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class HomeViewModel : ViewModelAmbient<HomeIntentAction, HomeIntentEvent>() {

    private val student = Student()

    override fun event(action: Int) {
        when (action) {
            GET_PROTO_DATA_STORE -> {
                getProtoDataStore()
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

    companion object {
        const val GET_PROTO_DATA_STORE = 0
    }
}