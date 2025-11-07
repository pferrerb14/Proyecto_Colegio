package com.leandro1995.seito.fcm.firestore

import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.fcm.firestore.config.Setting
import com.leandro1995.seito.fcm.firestore.util.RouteCollection
import com.leandro1995.seito.model.entity.SubTheme

class ThemeFirestoreFCM : FirestoreAmbientFCM() {

    fun subThemeArrayList(
        idCourse: String,
        id: String,
        success: (subThemeArrayList: ArrayList<SubTheme>) -> Unit,
        error: () -> Unit
    ) {
        val subThemeArrayList = arrayListOf<SubTheme>()

        collection(document = RouteCollection.subThemeRute(idCourse = idCourse, id = id)).get()
            .addOnSuccessListener { result ->
                result.forEach {
                    subThemeArrayList.add(
                        SubTheme(
                            id = it.id, name = toString(documentSnapshot = it, field = Setting.NAME)
                        )
                    )
                }
                success(subThemeArrayList)
            }.addOnFailureListener { error() }
    }
}