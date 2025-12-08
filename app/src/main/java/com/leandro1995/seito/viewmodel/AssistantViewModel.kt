package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.intent.action.AssistantIntentAction
import com.leandro1995.seito.intent.event.AssistantIntentEvent
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class AssistantViewModel : ViewModelAmbient<AssistantIntentAction, AssistantIntentEvent>() {

    val student = Student()

    override fun event(action: Int) {
        when (action) {
            ITEM_ONE_ADD -> {
                itemOneAdd()
            }
        }
    }

    private fun itemOneAdd() {
        value(action = AssistantIntentAction(fullName = student.fullName()))
    }

    companion object {
        const val ITEM_ONE_ADD = 0
    }
}