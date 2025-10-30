package com.leandro1995.seito.fcm.firestore.ambient

import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.getField

abstract class FirestoreAmbientFCM {
    private val firestore = Firebase.firestore

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

    protected fun collection(document: String) = firestore.collection(document)

    protected fun toString(documentSnapshot: DocumentSnapshot, field: String) =
        documentSnapshot.getString(field).orEmpty()

    protected fun toInt(documentSnapshot: DocumentSnapshot, field: String) =
        documentSnapshot.getField<Int>(field) ?: -1
}