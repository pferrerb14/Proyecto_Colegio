package com.leandro1995.seito.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Course(
    val id: String = "",
    val name: String = "",
    val imageUrl: String = "",
    val videoUrl: String = "",
    val theme: Theme = Theme()
) : Parcelable