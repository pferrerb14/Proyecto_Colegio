package com.leandro1995.seito.fcm.firestore

import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.fcm.firestore.config.Setting
import com.leandro1995.seito.fcm.firestore.util.RouteCollection
import com.leandro1995.seito.model.entity.Student

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
}