package com.leandro1995.seito.fcm.firestore

import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.fcm.firestore.config.Setting

class QuestionFirestoreFCM : FirestoreAmbientFCM() {

    fun questionDelete(id: String, success: () -> Unit, error: () -> Unit) {
        collection(document = Setting.BALLOT).document(id).delete()
            .addOnSuccessListener { success() }.addOnFailureListener { error() }
    }
}