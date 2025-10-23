package com.leandro1995.seito.model.entity

import com.leandro1995.seito.model.entity.ambient.User

class Student(
    name: String = "",
    lastName: String = "",
    email: String = "",
    age: Int = -1,
    sex: String = "",
    code: String = "",
    password: String = "",
    val teacher: Teacher = Teacher(),
    val coins: Int = -1
) : User(
    name = name,
    lastName = lastName,
    email = email,
    age = age,
    sex = sex,
    code = code,
    password = password
)