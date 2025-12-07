package com.leandro1995.seito.fcm.firestore

import android.util.Log
import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.model.entity.Question

class NoteFirestoreFCM : FirestoreAmbientFCM() {

    fun questionArrayList(
        idNote: String, success: (ArrayList<Question>) -> Unit, error: () -> Unit
    ) {
        Log.e("ENTRAAAAA", "${idNote} <------------------ LLEGO")
    }
}