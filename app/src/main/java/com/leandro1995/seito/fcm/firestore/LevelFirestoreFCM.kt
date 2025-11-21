package com.leandro1995.seito.fcm.firestore

import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.fcm.firestore.config.Setting
import com.leandro1995.seito.model.entity.Option
import com.leandro1995.seito.model.entity.Question

class LevelFirestoreFCM : FirestoreAmbientFCM() {

    fun questionArrayList(success: (ArrayList<Question>) -> Unit, error: () -> Unit) {
        val questionArrayList = arrayListOf<Question>()
        collection(document = Setting.BALLOT).get().addOnSuccessListener { result ->
            result.forEach {
                questionArrayList.add(
                    Question(
                        id = it.id,
                        imageUrl = toString(documentSnapshot = it, field = Setting.IMAGE_URL),
                        name = toString(documentSnapshot = it, field = Setting.NAME),
                        optionArrayList = optionArrayList(
                            optionArrayString = toArray(
                                documentSnapshot = it, field = Setting.OPTION_ARRAY
                            ), answer = toString(documentSnapshot = it, field = Setting.ANSWER)
                        ),
                        answer = toString(documentSnapshot = it, field = Setting.ANSWER),
                        idLevel = toString(documentSnapshot = it, field = Setting.ID_LEVEL),
                        coins = toInt(documentSnapshot = it, field = Setting.COINS)
                    )
                )
            }

            success(questionArrayList)
        }.addOnFailureListener { error() }
    }

    private fun optionArrayList(
        optionArrayString: Array<String>, answer: String
    ): ArrayList<Option> {
        val optionArrayList = arrayListOf<Option>()

        optionArrayString.forEach {
            optionArrayList.add(Option(isAnswer = answer == it, it))
        }

        return optionArrayList
    }
}