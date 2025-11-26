package com.leandro1995.seito.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Option(
    var isAnswer: Boolean = false, var name: String = "", var isCoin: Boolean = false
) : Parcelable {

    fun isName() = name.isEmpty()
}