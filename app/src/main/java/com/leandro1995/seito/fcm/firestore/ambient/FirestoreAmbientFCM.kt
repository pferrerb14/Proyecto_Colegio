package com.leandro1995.seito.fcm.firestore.ambient

import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.getField
import com.leandro1995.seito.fcm.firestore.config.Setting
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Option

abstract class FirestoreAmbientFCM {
    private val firestore = Firebase.firestore

    fun courseArrayList(success: (ArrayList<Course>) -> Unit, error: () -> Unit) {
        val courseArrayList = arrayListOf<Course>()
        collection(document = Setting.COURSE).get().addOnSuccessListener { result ->
            result.forEach {
                courseArrayList.add(
                    Course(
                        id = it.id, name = toString(documentSnapshot = it, field = Setting.NAME)
                    )
                )
            }
            success(courseArrayList)
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

    protected fun optionArrayList(optionArrayString: Array<String>): ArrayList<Option> {
        val optionArrayList = arrayListOf<Option>()

        optionArrayString.forEach {
            optionArrayList.add(Option(name = it))
        }

        return optionArrayList
    }

    protected fun collection(document: String) = firestore.collection(document)

    protected val addObject = hashMapOf<String, Any>()

    protected fun toString(documentSnapshot: DocumentSnapshot, field: String) =
        documentSnapshot.getString(field).orEmpty()

    protected fun toInt(documentSnapshot: DocumentSnapshot, field: String) =
        documentSnapshot.getField<Int>(field) ?: -1

    protected fun toBoolean(documentSnapshot: DocumentSnapshot, field: String) =
        documentSnapshot.getField<Boolean>(field) ?: false

    protected inline fun <reified T> toArray(
        documentSnapshot: DocumentSnapshot, field: String
    ): Array<T> =
        (documentSnapshot.get(field) as? List<*> ?: emptyList<Any?>()).filterIsInstance<T>()
            .toTypedArray()
}