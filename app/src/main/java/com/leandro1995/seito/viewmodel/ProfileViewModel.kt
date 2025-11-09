package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.ProfileIntentAction
import com.leandro1995.seito.intent.event.ProfileIntentEvent
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.model.entity.Teacher
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ProfileViewModel : ViewModelAmbient<ProfileIntentAction, ProfileIntentEvent>() {

    private val student = Student()
    private val teacher = Teacher()
    private var isUserType = false

    override fun event(action: Int) {
        when (action) {
            CLEAN_PROTO_DATA_STORE -> {
                cleanProtoDataStore()
            }

            GET_PROTO_DATA_STORE -> {
                viewDateUser()
            }
        }
    }

    fun protoDataStore(
        isUserType: Boolean, name: String, lastName: String, email: String, coins: Int, code: String
    ) {
        this.isUserType = isUserType

        if (this.isUserType) {
            student.name = name
            student.lastName = lastName
            student.email = email
            student.coins = coins
        } else {
            teacher.name = name
            teacher.lastName = lastName
            teacher.email = email
            teacher.code = code
        }
    }

    private fun cleanProtoDataStore() {
        emit(event = ProfileIntentEvent.CleanProtoDataStore)
    }

    private fun viewDateUser() {
        if (isUserType) {
            value(action = ProfileIntentAction(student = student))
        } else {
            value(action = ProfileIntentAction(teacher = teacher))
        }
    }

    companion object {
        const val CLEAN_PROTO_DATA_STORE = 0
        const val GET_PROTO_DATA_STORE = 2
    }
}