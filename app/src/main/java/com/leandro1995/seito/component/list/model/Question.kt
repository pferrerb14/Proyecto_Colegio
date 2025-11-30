package com.leandro1995.seito.component.list.model

class Question(
    var checked: Boolean = false,
    var id: String = "",
    var name: String = "",
    var answer: String = "",
    var coins: Int = -1,
    var isEnable: Boolean = true
)