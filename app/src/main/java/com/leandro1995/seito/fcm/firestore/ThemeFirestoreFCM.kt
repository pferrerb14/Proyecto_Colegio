package com.leandro1995.seito.fcm.firestore

import android.util.Log
import com.leandro1995.seito.fcm.firestore.ambient.FirestoreAmbientFCM
import com.leandro1995.seito.fcm.firestore.util.RouteCollection

class ThemeFirestoreFCM : FirestoreAmbientFCM() {

    fun subThemeArrayList(idCourse: String, id: String) {
        Log.e("ENTRAAA", " ${RouteCollection.themeRute(idCourse = idCourse)} - ${id} <--------------")
    }
}