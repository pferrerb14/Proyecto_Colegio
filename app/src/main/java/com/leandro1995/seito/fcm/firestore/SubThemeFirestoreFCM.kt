package com.leandro1995.seito.fcm.firestore

import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.fcm.firestore.config.Setting
import com.leandro1995.seito.fcm.firestore.util.RouteCollection
import com.leandro1995.seito.model.entity.Level

class SubThemeFirestoreFCM : FirestoreAmbientFCM() {

    fun levelArrayList(
        idCourse: String,
        idTheme: String,
        idSubTheme: String,
        success: (levelArrayList: ArrayList<Level>) -> Unit,
        error: () -> Unit
    ) {
        val levelArrayList = arrayListOf<Level>()
        collection(
            document = RouteCollection.levelRute(
                idCourse = idCourse, idTheme = idTheme, idSubTeme = idSubTheme
            )
        ).get().addOnSuccessListener { result ->
            result.forEach {
                levelArrayList.add(
                    Level(
                        id = it.id, name = toString(documentSnapshot = it, field = Setting.NAME)
                    )
                )
            }
            success(levelArrayList)
        }.addOnFailureListener { error() }
    }
}