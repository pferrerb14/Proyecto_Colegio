package com.leandro1995.seito.model.entity

import android.os.Parcelable
import com.leandro1995.seito.fcm.firestore.NoteFirestoreFCM
import kotlinx.parcelize.Parcelize

@Parcelize
class Note(
    var id: String = "",
    var date: String = "",
    var idGroup: String = "",
    var note: Double = -1.0,
    var timer: String = ""
) : Parcelable {
    fun answerFirebaseArrayList(
        email: String,
        idCollection: String,
        success: (ArrayList<Answer>) -> Unit,
        error: () -> Unit
    ) {
        NoteFirestoreFCM().answerArrayList(
            email = email,
            idCollection = idCollection,
            idNote = id,
            success = success,
            error = error
        )
    }
}