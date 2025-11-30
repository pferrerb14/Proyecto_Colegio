package com.leandro1995.seito.model.entity

import android.os.Parcelable
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.fcm.firestore.LevelFirestoreFCM
import kotlinx.parcelize.Parcelize

@Parcelize
class Level(val id: String = "", val name: String = "") : Parcelable {

    fun questionFirebase(
        success: (questionArrayList: ArrayList<Question>) -> Unit, error: () -> Unit
    ) {
        LevelFirestoreFCM().questionArrayList(
            idLevel = id, success = success, error = error
        )
    }

    fun randomQuestionFirebase(
        success: (questionArrayList: ArrayList<Question>) -> Unit, error: () -> Unit
    ) {
        questionFirebase(success = {
            success(
                ArrayList(
                    it.shuffled().take(Setting.QUESTION_LENGTH)
                )
            )
        }, error = error)
    }
}