package com.leandro1995.seito.intent.action

import com.leandro1995.seito.model.entity.Question

class QuestionAnswerIntentAction(
    val questionArrayList: ArrayList<Question>? = null,
    val position: Int? = null,
    val coin: Int? = null,
    val isEnableNextButton: Boolean? = null
)