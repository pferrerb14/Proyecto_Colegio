package com.leandro1995.seito.component.list.model

import com.leandro1995.seito.model.entity.Teacher

class Student(
    var name: String = "",
    var lastName: String = "",
    var email: String = "",
    var age: Int = -1,
    var sex: String = "",
    var password: String = "",
    var teacher: Teacher = Teacher(),
    var coins: Int = -1
)