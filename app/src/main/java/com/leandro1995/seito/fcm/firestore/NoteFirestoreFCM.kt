package com.leandro1995.seito.fcm.firestore

import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.fcm.firestore.config.Setting
import com.leandro1995.seito.model.entity.Answer

class NoteFirestoreFCM : FirestoreAmbientFCM() {

    fun answerArrayList(
        email: String,
        idNote: String,
        idCollection: String,
        success: (ArrayList<Answer>) -> Unit,
        error: () -> Unit
    ) {
        collection(document = "${Setting.ANSWER}/${email}/${idCollection}/${idNote}/${Setting.ANSWER}").get()
            .addOnSuccessListener { result ->
                val answerArrayList = arrayListOf<Answer>()
                result.forEach {
                    answerArrayList.add(
                        Answer(
                            id = it.id,
                            imageUrl = toString(documentSnapshot = it, field = Setting.IMAGE_URL),
                            name = toString(documentSnapshot = it, field = Setting.NAME),
                            answer = toString(documentSnapshot = it, field = Setting.ANSWER),
                            isAnswer = toBoolean(documentSnapshot = it, field = Setting.IS_ANSWER)
                        )
                    )
                }
                success(answerArrayList)
            }.addOnFailureListener {
                error()
            }
    }
}