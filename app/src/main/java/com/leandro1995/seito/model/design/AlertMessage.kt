package com.leandro1995.seito.model.design

import androidx.annotation.StringRes
import com.leandro1995.seito.model.design.ambient.Message

class AlertMessage(@StringRes idMessage: Int, isCancelable: Boolean = true) :
    Message(idMessage = idMessage, isCancelable = isCancelable)