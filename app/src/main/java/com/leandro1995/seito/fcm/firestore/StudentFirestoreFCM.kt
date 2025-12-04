package com.leandro1995.seito.fcm.firestore

import com.google.firebase.firestore.DocumentReference
import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.fcm.firestore.config.Setting
import com.leandro1995.seito.model.entity.Answer
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.util.trustedtime.TrustedTime

class StudentFirestoreFCM : FirestoreAmbientFCM() {

    fun courseVideoArrayList(
        success: (courseVideoArrayList: ArrayList<Course>) -> Unit, error: () -> Unit
    ) {

        val courseArrayList = arrayListOf<Course>()

        collection(document = Setting.VIDEO_COURSE).get().addOnSuccessListener { result ->
            result.forEach {
                courseArrayList.add(
                    Course(
                        name = toString(documentSnapshot = it, Setting.NAME),
                        imageUrl = toString(documentSnapshot = it, Setting.IMAGE_URL),
                        videoUrl = toString(documentSnapshot = it, Setting.VIDEO_URL)
                    )
                )
            }
            success(courseArrayList)
        }.addOnFailureListener {
            error()
        }
    }

    fun addAnswerFirebase(
        idGroup: String,
        note: Double,
        email: String,
        document: String,
        timeSkip: String,
        answerArrayList: ArrayList<Answer>,
        success: () -> Unit,
        error: () -> Unit
    ) {
        addObject[Setting.NOTE] = note
        addObject[Setting.DATE] =
            TrustedTime.date(format = com.leandro1995.seito.config.Setting.DATE_FORMAT)
        addObject[Setting.TIMER] = timeSkip
        addObject[Setting.ID_GROUP] = idGroup

        collection(document = Setting.ANSWER).document(email).collection(document).add(addObject)
            .addOnSuccessListener { result ->
                addAnswerQuestion(
                    documentReference = result,
                    answerArrayList = answerArrayList,
                    success = success,
                    error = error
                )
            }.addOnFailureListener {
                error()
            }
    }

    fun updateCoinFirebase(coin: Int, email: String, success: (Int) -> Unit, error: () -> Unit) {
        addObject[Setting.COINS] = coin
        collection(Setting.USERS).document(email).update(addObject).addOnSuccessListener {
            success(coin)
        }.addOnFailureListener {
            error()
        }
    }

    private fun addAnswerQuestion(
        documentReference: DocumentReference,
        answerArrayList: ArrayList<Answer>,
        position: Int = 0,
        success: () -> Unit,
        error: () -> Unit
    ) {
        answerArrayList.getOrNull(position)?.let {
            addObject.clear()
            addObject[Setting.IMAGE_URL] = it.imageUrl
            addObject[Setting.NAME] = it.name
            addObject[Setting.ANSWER] = it.answer
            addObject[Setting.IS_ANSWER] = it.isAnswer
            documentReference.collection(Setting.ANSWER).add(addObject).addOnSuccessListener {
                addAnswerQuestion(
                    documentReference = documentReference,
                    answerArrayList = answerArrayList,
                    position = position + 1,
                    success = success,
                    error = error
                )
            }.addOnFailureListener {
                error()
            }
        } ?: run {
            success()
        }
    }
}