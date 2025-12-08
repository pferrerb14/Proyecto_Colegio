package com.leandro1995.seito.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Answer(
    var id: String = "",
    var imageUrl: String = "",
    var isAnswer: Boolean = false,
    var name: String = "",
    var answer: String = ""
) : Parcelable