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

    fun addAnswerFirebase(
        idGroup: String,
        questionArrayList: ArrayList<Question>,
        timeSkip: String,
        document: String,
        success: () -> Unit,
        error: () -> Unit
    ) {
        val point = (Setting.NOTE_MAXIMUM / questionArrayList.size).toDouble()
        val answerArrayList = arrayListOf<Answer>()
        var note = 0.0

        questionArrayList.forEach {
            if (it.optionArrayList.find { option -> option.isAnswer }?.name == it.answer) {
                note = note + point
            }

            answerArrayList.add(
                Answer(
                    imageUrl = it.imageUrl,
                    name = it.name,
                    answer = it.answer,
                    isAnswer = it.optionArrayList.find { option -> option.isAnswer }?.name == it.answer
                )
            )
        }

        StudentFirestoreFCM().addAnswerFirebase(
            idGroup = idGroup,
            note = note,
            email = email,
            document = document,
            answerArrayList = answerArrayList,
            timeSkip = timeSkip,
            success = success,
            error = error
        )
    }

    fun updateCoinFirebase(
        questionArrayList: ArrayList<Question>, success: (Int) -> Unit, error: () -> Unit
    ) {
        StudentFirestoreFCM().updateCoinFirebase(
            coin = calculatePoint(questionArrayList = questionArrayList),
            email = email,
            success = success,
            error = error
        )
    }

    fun examExistsFirebase(
        idExam: String, success: (Boolean) -> Unit, error: () -> Unit
    ) {
        StudentFirestoreFCM().examExistsFirebase(
            idExam = idExam, email = email, success = success, error = error
        )
    }

    fun noteFirebaseArrayList(
        idCollection: String, success: (ArrayList<Note>) -> Unit, error: () -> Unit
    ) {
        StudentFirestoreFCM().noteArrayList(
            email = email, idCollection = idCollection, success = success, error = error
        )
    }

    fun isEqualPassword(confirmPassword: String) = password == confirmPassword

    fun isEmptyAge() = age == -1

    fun isAgeRange() = age in 6..18

    fun isCoins() = coins == -1

    private fun calculatePoint(questionArrayList: ArrayList<Question>): Int {
        var coinCalculate = 0

        questionArrayList.forEach {
            if (it.optionArrayList.find { option -> option.isAnswer && !option.isCoin }?.name == it.answer) {
                coinCalculate = coinCalculate + Setting.DISCOUNT_CURRENCY
            }
        }

        return coinCalculate + coins
    }
}