package com.leandro1995.seito.model.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Level(val id: String = "", val name: String = "", val isType: Boolean = false) : Parcelable