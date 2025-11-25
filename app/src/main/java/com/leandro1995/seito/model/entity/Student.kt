package com.leandro1995.seito.model.entity

import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.fcm.firestore.StudentFirestoreFCM
import com.leandro1995.seito.model.entity.ambient.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    override var name: String = "",
    override var lastName: String = "",
    override var email: String = "",
    override var age: Int = -1,
    override var sex: String = "",
    override var password: String = "",
    var teacher: Teacher = Teacher(),
    var coins: Int = -1
) : User(
    name = name, lastName = lastName, email = email, age = age, sex = sex, password = password
) {

    fun courseVideoFirebaseArrayList(
        success: (courseArrayList: ArrayList<Course>) -> Unit, error: () -> Unit
    ) {
        StudentFirestoreFCM().courseVideoArrayList(success = success, error = error)
    }

    fun addAnswerFirebase(questionArrayList: ArrayList<Question>) {
        val point = Setting.NOTE_MAXIMUM / questionArrayList.size
        var note = 0

        questionArrayList.forEach {
            if (it.optionArrayList.find { option -> option.isAnswer }?.name == it.answer) {
                note = note + point
            }
        }
    }

    fun isEqualPassword(confirmPassword: String) = password == confirmPassword

    fun isEmptyAge() = age == -1

    fun isAgeRange() = age in 6..18

    fun isCoins() = coins == -1
}