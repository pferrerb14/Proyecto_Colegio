package com.leandro1995.seito.fcm.firestore

import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.fcm.firestore.config.Setting
import com.leandro1995.seito.model.entity.Question

class NoteFirestoreFCM : FirestoreAmbientFCM() {

    fun questionArrayList(
        email: String,
        idNote: String,
        idCollection: String,
        success: (ArrayList<Question>) -> Unit,
        error: () -> Unit
    ) {
        collection(document = "${Setting.ANSWER}/${email}/${idCollection}/${idNote}/${Setting.ANSWER}").get()
            .addOnSuccessListener { result ->
                val questionArrayList = arrayListOf<Question>()
                result.forEach {
                    questionArrayList.add(
                        Question(
                            id = it.id,
                            imageUrl = toString(documentSnapshot = it, field = Setting.IMAGE_URL),
                            name = toString(documentSnapshot = it, field = Setting.NAME),
                            optionArrayList = optionArrayList(
                                optionArrayString = toArray(
                                    documentSnapshot = it, field = Setting.OPTION_ARRAY
                                )
                            ),
                            answer = toString(documentSnapshot = it, field = Setting.ANSWER),
                            idLevel = toString(documentSnapshot = it, field = Setting.ID_LEVEL),
                            coins = toInt(documentSnapshot = it, field = Setting.COINS)
                        )
                    )
                }
                success(questionArrayList)
            }.addOnFailureListener {
                error()
            }
    }
}