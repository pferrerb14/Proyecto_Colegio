package com.leandro1995.seito.fcm.firestore

import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.fcm.firestore.config.Setting
import com.leandro1995.seito.model.entity.Course

class StudentFirestoreFCM : FirestoreAmbientFCM() {

    fun courseVideoArrayList(success: (courseVideoArrayList: ArrayList<Course>) -> Unit, error: () -> Unit) {

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
}