package com.leandro1995.seito.model.entity

import android.os.Parcelable
import com.leandro1995.seito.fcm.firestore.ThemeFirestoreFCM
import kotlinx.parcelize.Parcelize

@Parcelize
class Theme(val id: String = "", val name: String = "") : Parcelable {

    fun subThemeFirebase(
        idCourse: String,
        success: (subThemeArrayList: ArrayList<SubTheme>) -> Unit,
        error: () -> Unit
    ) {
        ThemeFirestoreFCM().subThemeArrayList(
            idCourse = idCourse, id = id, success = success, error = error
        )
    }
}