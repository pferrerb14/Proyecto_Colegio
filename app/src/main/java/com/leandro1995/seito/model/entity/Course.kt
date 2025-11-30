package com.leandro1995.seito.model.entity

import android.os.Parcelable
import com.leandro1995.seito.fcm.firestore.CourseFirestoreFCM
import kotlinx.parcelize.Parcelize

@Parcelize
class Course(
    val id: String = "", val name: String = "", val imageUrl: String = "", val videoUrl: String = ""
) : Parcelable {

    fun themeFirebase(success: (ArrayList<Theme>) -> Unit, error: () -> Unit) {
        CourseFirestoreFCM().themeArrayList(id = id, success = success, error = error)
    }

    fun isIdEmpty() = id.isEmpty()
}