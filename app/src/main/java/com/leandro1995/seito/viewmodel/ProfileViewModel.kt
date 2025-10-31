package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.event.ProfileIntentEvent
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class ProfileViewModel : ViewModelAmbient<Any, ProfileIntentEvent>() {

    override fun event(action: Int) {
        when (action) {
            CLEAN_PROTO_DATA_STORE -> {
                cleanProtoDataStore()
            }
        }
    }

    private fun cleanProtoDataStore() {
        emit(event = ProfileIntentEvent.CleanProtoDataStore)
    }

    companion object {
        const val CLEAN_PROTO_DATA_STORE = 0
    }
}