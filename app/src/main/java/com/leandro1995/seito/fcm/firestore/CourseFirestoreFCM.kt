package com.leandro1995.seito.fcm.firestore

import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.fcm.firestore.config.Setting
import com.leandro1995.seito.fcm.firestore.util.RouteCollection
import com.leandro1995.seito.model.entity.Theme

class CourseFirestoreFCM : FirestoreAmbientFCM() {

    fun themeArrayList(id: String, success: (ArrayList<Theme>) -> Unit, error: () -> Unit) {
        val themeArrayList = arrayListOf<Theme>()
        collection(document = RouteCollection.themeRute(idCourse = id)).get()
            .addOnSuccessListener { result ->
                result.forEach {
                    themeArrayList.add(
                        Theme(
                            id = it.id, name = toString(documentSnapshot = it, field = Setting.NAME)
                        )
                    )
                }
                success(themeArrayList)
            }.addOnFailureListener { error() }
    }
}