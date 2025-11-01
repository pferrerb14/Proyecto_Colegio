package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.ProfileIntentAction
import com.leandro1995.seito.intent.event.ProfileIntentEvent
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ProfileViewModel : ViewModelAmbient<ProfileIntentAction, ProfileIntentEvent>() {

    private val student = Student()

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

    fun protoDataStore(name: String, lastName: String, email: String, coins: Int) {
        student.name = name
        student.lastName = lastName
        student.email = email
        student.coins = coins
    }

    private fun cleanProtoDataStore() {
        emit(event = ProfileIntentEvent.CleanProtoDataStore)
    }

    private fun viewDateUser() {
        value(action = ProfileIntentAction(student = student))
    }

    companion object {
        const val CLEAN_PROTO_DATA_STORE = 0
        const val GET_PROTO_DATA_STORE = 2
    }
}