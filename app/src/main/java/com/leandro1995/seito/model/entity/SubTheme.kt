package com.leandro1995.seito.model.entity

import android.os.Parcelable
import com.leandro1995.seito.fcm.firestore.SubThemeFirestoreFCM
import kotlinx.parcelize.Parcelize

@Parcelize
class SubTheme(val id: String = "", val name: String = "") : Parcelable {

    fun levelFirebase(
        idCourse: String,
        idTheme: String,
        success: (levelArrayList: ArrayList<Level>) -> Unit,
        error: () -> Unit
    ) {
        SubThemeFirestoreFCM().levelArrayList(
            idCourse = idCourse,
            idTheme = idTheme,
            idSubTheme = id,
            success = success,
            error = error
        )
    }
}