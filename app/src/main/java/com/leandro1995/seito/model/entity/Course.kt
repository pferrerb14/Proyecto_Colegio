package com.leandro1995.seito.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Course(val name: String, val imageUrl: String, val videoUrl: String) : Parcelable