package com.leandro1995.seito.model.entity

import android.os.Parcelable
import com.leandro1995.seito.fcm.firestore.LevelFirestoreFCM
import kotlinx.parcelize.Parcelize

@Parcelize
class Level(val id: String = "", val name: String = "", val isType: Boolean = false) : Parcelable {

    fun questionFirebase(
        success: (questionArrayList: ArrayList<Question>) -> Unit, error: () -> Unit
    ) {
        LevelFirestoreFCM().questionArrayList(success = success, error = error)
    }
}