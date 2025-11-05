package com.leandro1995.seito.fcm.firestore.ambient

import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.getField
import com.leandro1995.seito.fcm.firestore.config.Setting
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Theme

abstract class FirestoreAmbientFCM {
    private val firestore = Firebase.firestore

    fun courseArrayList(success: (ArrayList<Course>) -> Unit, error: () -> Unit) {
        val courseArrayList = arrayListOf<Course>()
        collection(document = Setting.COURSE).get().addOnSuccessListener { result ->
            result.forEach {
                themeArrayList(idCourse = it.id, success = { themeArrayList ->
                    courseArrayList.add(
                        Course(
                            id = it.id,
                            name = toString(documentSnapshot = it, field = Setting.NAME),
                            themeArrayList = themeArrayList
                        )
                    )
                    success(courseArrayList)
                }, error = error)
            }
        }.addOnFailureListener { error() }
    }

    protected fun whereEqualTo(
        document: String,
        field: String,
        value: String,
        success: (documentSnapshot: List<DocumentSnapshot>) -> Unit,
        error: () -> Unit
    ) {
        collection(document = document).whereEqualTo(field, value).get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    success(result.documents)
                } else {
                    error()
                }
            }.addOnFailureListener {
                error()
            }
    }

    private fun themeArrayList(
        idCourse: String, success: (ArrayList<Theme>) -> Unit, error: () -> Unit
    ) {
        val themeArrayList = arrayListOf<Theme>()
        collection(document = "${Setting.COURSE}/$idCourse/${idCourse}_${Setting.THEME}").get()
            .addOnSuccessListener { result ->
                result.forEach {
                    themeArrayList.add(
                        Theme(
                            id = it.id, name = toString(documentSnapshot = it, field = Setting.NAME)
                        )
                    )
                }
                success(themeArrayList)
            }.addOnFailureListener { error() }
    }

    protected fun collection(document: String) = firestore.collection(document)

    protected val addObject = hashMapOf<String, Any>()

    protected fun toString(documentSnapshot: DocumentSnapshot, field: String) =
        documentSnapshot.getString(field).orEmpty()

    protected fun toInt(documentSnapshot: DocumentSnapshot, field: String) =
        documentSnapshot.getField<Int>(field) ?: -1
}