package com.leandro1995.seito.model.entity

import android.os.Parcelable
import com.leandro1995.seito.fcm.authentication.AuthenticationFCM
import com.leandro1995.seito.fcm.firestore.TeacherFirestoreFCM
import com.leandro1995.seito.fcm.firestore.UserFirestoreFCM
import com.leandro1995.seito.model.entity.ambient.User
import kotlinx.parcelize.Parcelize

@Parcelize
data class Teacher(
    override var name: String = "",
    override var lastName: String = "",
    override var email: String = "",
    override var age: Int = -1,
    override var sex: String = "",
    override var password: String = "",
    var code: String = ""
) : User(
    name = name, lastName = lastName, email = email, age = age, sex = sex, password = password
), Parcelable {

    fun codeVerifyFirebase(success: (teacher: Teacher) -> Unit, error: () -> Unit) {
        UserFirestoreFCM().whereEqualToCode(code = code, success = success, error = error)
    }

    fun addStudentFirebase(student: Student, success: () -> Unit, error: () -> Unit) {
        student.teacher = this
        TeacherFirestoreFCM().documentSet(student = student, success = success, error = error)
    }

    fun createUserStudentFirebase(student: Student, success: () -> Unit, error: () -> Unit) {
        AuthenticationFCM().createUserWithEmailAndPassword(
            email = student.email, password = student.password, success = success, error = error
        )
    }

    fun addThemeFirebase(
        idCourse: String, themeName: String, success: () -> Unit, error: () -> Unit
    ) {
        TeacherFirestoreFCM().themeAdd(
            idCourse = idCourse, themeName = themeName, success = success, error = error
        )
    }

    fun addSubThemeFirebase(
        idCourse: String, themeName: String, idTheme: String, success: () -> Unit, error: () -> Unit
    ) {
        TeacherFirestoreFCM().subthemeAdd(
            idCourse = idCourse,
            themeName = themeName,
            idTheme = idTheme,
            success = success,
            error = error
        )
    }

    fun addLevelFirebase(
        idCourse: String,
        idTheme: String,
        idSubTheme: String,
        levelStringArrayList: ArrayList<String>,
        success: () -> Unit,
        error: () -> Unit
    ) {
        TeacherFirestoreFCM().levelAdd(
            idCourse = idCourse,
            idTheme = idTheme,
            idSubTheme = idSubTheme,
            levelStringArrayList = levelStringArrayList,
            success = success,
            error = error
        )
    }

    fun studentFirebaseArrayList(success: (ArrayList<Student>) -> Unit, error: () -> Unit) {
        TeacherFirestoreFCM().studentArrayList(success = success, error = error)
    }

    fun addQuestionFirebase(question: Question, success: () -> Unit, error: () -> Unit) {
        TeacherFirestoreFCM().questionAdd(question = question, success = success, error = error)
    }

    fun addExamFirebase(
        exam: Exam, questionArrayList: ArrayList<Question>, success: () -> Unit, error: () -> Unit
    ) {
        TeacherFirestoreFCM().examAdd(
            exam = exam, questionArrayList = questionArrayList, success = success, error = error
        )
    }

    fun isCode() = code.isEmpty()

    fun isCodeLength(length: Int) = code.length == length
}