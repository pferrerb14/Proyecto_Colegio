package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Chat

interface AssistantIntentActionCallBack {

    fun startList()
    fun fullName(fullName: String)
    fun chatArrayList(chatArrayList: ArrayList<Chat>)
}