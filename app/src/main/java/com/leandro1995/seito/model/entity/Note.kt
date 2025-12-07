package com.leandro1995.seito.model.entity

import com.leandro1995.seito.fcm.firestore.NoteFirestoreFCM

class Note(
    var id: String = "",
    var date: String = "",
    var idGroup: String = "",
    var note: Double = -1.0,
    var timer: String = ""
) {
    fun questionFirebaseArrayList(success: (ArrayList<Question>) -> Unit, error: () -> Unit) {
        NoteFirestoreFCM().questionArrayList(idNote = id, success = success, error = error)
    }
}