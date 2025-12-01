package com.leandro1995.seito.fcm.firestore

import com.google.firebase.firestore.DocumentReference
import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.fcm.firestore.config.Setting
import com.leandro1995.seito.fcm.firestore.util.RouteCollection
import com.leandro1995.seito.model.entity.Exam
import com.leandro1995.seito.model.entity.Question
import com.leandro1995.seito.model.entity.Student
import com.leandro1995.seito.model.entity.Teacher

class TeacherFirestoreFCM : FirestoreAmbientFCM() {

    fun documentSet(student: Student, success: () -> Unit, error: () -> Unit) {
        addObject[Setting.NAME] = student.name
        addObject[Setting.LAST_NAME] = student.lastName
        addObject[Setting.EMAIL] = student.email
        addObject[Setting.AGE] = student.age
        addObject[Setting.SEX] = student.sex
        addObject[Setting.TEACHER] = student.teacher.fullName()
        addObject[Setting.COINS] = 0

        collection(document = Setting.USERS).document(student.email).set(addObject)
            .addOnSuccessListener {
                success()
            }.addOnFailureListener {
                error()
            }
    }

    fun themeAdd(idCourse: String, themeName: String, success: () -> Unit, error: () -> Unit) {
        addObject[Setting.NAME] = themeName

        collection(document = RouteCollection.themeRute(idCourse = idCourse)).add(addObject)
            .addOnSuccessListener {
                success()
            }.addOnFailureListener {
                error()
            }
    }

    fun subthemeAdd(
        idCourse: String, themeName: String, idTheme: String, success: () -> Unit, error: () -> Unit
    ) {
        addObject[Setting.NAME] = themeName

        collection(
            document = RouteCollection.subThemeRute(
                idCourse = idCourse, idTheme = idTheme
            )
        ).add(addObject).addOnSuccessListener {
            success()
        }.addOnFailureListener {
            error()
        }
    }

    fun levelAdd(
        idCourse: String,
        idTheme: String,
        idSubTheme: String,
        position: Int = 0,
        levelStringArrayList: ArrayList<String>,
        success: () -> Unit,
        error: () -> Unit
    ) {

        levelStringArrayList.getOrNull(position)?.let {
            addObject[Setting.NAME] = it

            collection(
                document = RouteCollection.levelRute(
                    idCourse = idCourse, idTheme = idTheme, idSubTeme = idSubTheme
                )
            ).document().set(addObject).addOnSuccessListener {
                levelAdd(
                    idCourse = idCourse,
                    idTheme = idTheme,
                    idSubTheme = idSubTheme,
                    position = position + 1,
                    levelStringArrayList = levelStringArrayList,
                    success = success,
                    error = error
                )
            }.addOnFailureListener {
                error()
            }
        } ?: run {
            success()
        }
    }

    fun studentArrayList(success: (ArrayList<Student>) -> Unit, error: () -> Unit) {
        whereEqualTo(document = Setting.USERS, field = Setting.CODE, value = "", success = {
            val studentArrayList = arrayListOf<Student>()
            it.forEach { result ->
                studentArrayList.add(
                    Student(
                        name = toString(documentSnapshot = result, field = Setting.NAME),
                        lastName = toString(documentSnapshot = result, field = Setting.LAST_NAME),
                        email = toString(documentSnapshot = result, field = Setting.EMAIL),
                        age = toInt(documentSnapshot = result, field = Setting.AGE),
                        sex = toString(documentSnapshot = result, field = Setting.SEX),
                        teacher = Teacher(
                            name = toString(
                                documentSnapshot = result, field = Setting.TEACHER
                            )
                        ),
                        coins = toInt(documentSnapshot = result, field = Setting.COINS)
                    )
                )
            }
            success(studentArrayList)
        }, error = error)
    }

    fun questionAdd(question: Question, success: () -> Unit, error: () -> Unit) {
        addObject[Setting.IMAGE_URL] = question.imageUrl
        addObject[Setting.NAME] = question.name
        addObject[Setting.OPTION_ARRAY] = question.optionArrayList.map { it.name }
        addObject[Setting.ANSWER] = question.answer
        addObject[Setting.ID_LEVEL] = question.idLevel
        addObject[Setting.COINS] = question.coins

        collection(document = Setting.BALLOT).add(addObject).addOnSuccessListener { success() }
            .addOnFailureListener { error() }
    }

    fun examAdd(
        exam: Exam, questionArrayList: ArrayList<Question>, success: () -> Unit, error: () -> Unit
    ) {
        addObject[Setting.NAME] = exam.name
        collection(document = Setting.EXAM).add(addObject).addOnSuccessListener {
            examQuestionAdd(
                questionArrayList = questionArrayList,
                documentReference = it,
                success = success,
                error = error
            )
        }.addOnFailureListener {
            error()
        }
    }

    fun examArrayList(success: (ArrayList<Exam>) -> Unit, error: () -> Unit) {
        collection(document = Setting.EXAM).get().addOnSuccessListener {
            val examArrayList = arrayListOf<Exam>()
            it.forEach { result ->
                examArrayList.add(
                    Exam(
                        id = result.id,
                        name = toString(documentSnapshot = result, field = Setting.NAME)
                    )
                )
            }
            success(examArrayList)
        }.addOnFailureListener {
            error()
        }
    }

    fun examDelete(idExam: String, success: () -> Unit, error: () -> Unit) {
        collection(document = Setting.EXAM).document(idExam).delete()
            .addOnSuccessListener { success() }.addOnFailureListener { error() }
    }

    private fun examQuestionAdd(
        questionArrayList: ArrayList<Question>,
        documentReference: DocumentReference,
        position: Int = 0,
        success: () -> Unit,
        error: () -> Unit
    ) {
        questionArrayList.getOrNull(position)?.let { question ->
            addObject.clear()
            addObject[Setting.IMAGE_URL] = question.imageUrl
            addObject[Setting.NAME] = question.name
            addObject[Setting.OPTION_ARRAY] = question.optionArrayList.map { it.name }
            addObject[Setting.ANSWER] = question.answer
            addObject[Setting.ID_LEVEL] = question.idLevel
            addObject[Setting.COINS] = question.coins

            documentReference.collection(Setting.QUESTION).add(addObject).addOnSuccessListener {
                examQuestionAdd(
                    questionArrayList = questionArrayList,
                    documentReference = documentReference,
                    position = position + 1,
                    success = success,
                    error = error
                )
            }.addOnFailureListener { error() }
        } ?: run {
            success()
        }
    }
}