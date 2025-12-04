package com.leandro1995.seito.model.entity

import com.leandro1995.seito.fcm.firestore.ExamFirestoreFCM

class Exam(
    val id: String = "", var name: String = ""
) {
    fun questionFirebaseArrayList(success: (ArrayList<Question>) -> Unit, error: () -> Unit) {
        ExamFirestoreFCM().questionArrayList(idExam = id, success = success, error = error)
    }

    fun isNameEmpty() = name.isEmpty()
}