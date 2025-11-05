package com.leandro1995.seito.component.util

import android.content.res.TypedArray
import androidx.annotation.StyleableRes

object TypeArrayUtil {
    fun imageTypeArray(@StyleableRes idStyleableRes: Int, typedArray: TypedArray) =
        typedArray.getResourceId(idStyleableRes, 0)

    fun textTypeArray(@StyleableRes idStyleableRes: Int, typedArray: TypedArray) =
        typedArray.getString(idStyleableRes)

    fun colorTypeArray(@StyleableRes idStyleableRes: Int, typedArray: TypedArray) =
        typedArray.getColor(idStyleableRes, 0)
}