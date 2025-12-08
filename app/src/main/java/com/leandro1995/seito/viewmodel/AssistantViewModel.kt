package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.intent.action.AssistantIntentAction
import com.leandro1995.seito.intent.event.AssistantIntentEvent
import com.leandro1995.seito.model.entity.Chat
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class AssistantViewModel : ViewModelAmbient<AssistantIntentAction, AssistantIntentEvent>() {

    val student = Student()
    private val chatArrayList = arrayListOf<Chat>()

    override fun event(action: Int) {
        when (action) {
            ITEM_ONE_ADD -> {
                itemOneAdd()
            }
        }
    }

    fun addItem(message: String) {
        chatArrayList.add(Chat(message = message, type = Setting.ANSWER_CHAT))
        value(action = AssistantIntentAction(chatArrayList = chatArrayList))
    }

    private fun itemOneAdd() {
        value(action = AssistantIntentAction(fullName = student.fullName()))
    }

    companion object {
        const val ITEM_ONE_ADD = 0
    }
}