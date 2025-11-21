package com.leandro1995.seito.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Option(var isAnswer: Boolean = false, var name: String = "") : Parcelable {

    fun isName() = name.isEmpty()
}