package com.leandro1995.seito.model.design.ambient

import android.content.Context
import androidx.annotation.StringRes

open class Message(@param:StringRes private val idMessage: Int) {

    fun message(context: Context) = context.getString(idMessage)
}