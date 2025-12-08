package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.intent.action.AssistantIntentAction
import com.leandro1995.seito.intent.event.AssistantIntentEvent
import com.leandro1995.seito.intent.event.ambient.LoadingIntentEventAmbient
import com.leandro1995.seito.model.entity.Chat
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class AssistantViewModel : ViewModelAmbient<AssistantIntentAction, AssistantIntentEvent>() {

    val student = Student()
    var message = ""
    private val chatArrayList = arrayListOf<Chat>()

    override fun event(action: Int) {
        when (action) {
            ITEM_ONE_ADD -> {
                itemOneAdd()
            }

            ITEM_ADD -> {
                itemAdd()
            }
        }
    }

    override suspend fun service(idService: Int) {
        when (idService) {
            ITEM_ADD_SERVICE -> {
                solutionService()
            }
        }
    }

    fun addItem(message: String = "", type: Int) {
        chatArrayList.add(Chat(message = message, type = type))
        value(action = AssistantIntentAction(chatArrayList = chatArrayList))
    }

    private fun itemAdd() {
        addItem(message = message, type = Setting.USER_CHAT)
        loading(idService = ITEM_ADD_SERVICE, isDelayDisable = false)
    }

    private fun itemOneAdd() {
        value(action = AssistantIntentAction(fullName = student.fullName()))
    }

    private suspend fun solutionService() {
        addItem(type = Setting.LOADING_CHAT)
        student.solutionService(problemText = message, callback = { result ->
            chatArrayList.removeAt(chatArrayList.size - 1)
            addItem(
                message = result.joinToString("\n") { "${it.description}\n${it.operation}\n${it.result}" },
                type = Setting.ANSWER_CHAT
            )
            loading()
        }, error = {
            chatArrayList.removeAt(chatArrayList.size - 1)
            value(action = AssistantIntentAction(chatArrayList = chatArrayList))
            loading()
        })
    }

    override fun loading(idService: Int, isDelayDisable: Boolean) {
        emit(
            event = AssistantIntentEvent.Loading(
                loadingIntentEventAmbient = LoadingIntentEventAmbient.Loading(
                    loading = Loading(idService = idService, isDelayDisable = isDelayDisable)
                )
            )
        )
    }

    companion object {
        const val ITEM_ONE_ADD = 0
        const val ITEM_ADD = 1
        const val ITEM_ADD_SERVICE = 3
    }
}