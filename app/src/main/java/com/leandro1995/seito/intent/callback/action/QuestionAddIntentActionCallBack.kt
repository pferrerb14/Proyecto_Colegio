package com.leandro1995.seito.intent.callback.action

import com.leandro1995.seito.model.entity.Option

interface QuestionAddIntentActionCallBack {

    fun optionArrayList(optionArrayList: ArrayList<Option>)
    fun isOptionAddLink(isVisible: Boolean)
}