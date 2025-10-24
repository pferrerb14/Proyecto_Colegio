package com.leandro1995.seito.util.dialog

import android.content.Context
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.leandro1995.seito.R
import com.leandro1995.seito.model.design.AlertMessage

object AppUtilDialog {

    fun dialogMaterialDesign(context: Context, alertMessage: AlertMessage) {
        MaterialAlertDialogBuilder(context).setTitle(context.getString(R.string.app_name))
            .setMessage(alertMessage.message(context = context))
            .setPositiveButton(context.getString(R.string.accept_button)) { dialog, which ->
                dialog.dismiss()
            }.show()
    }
}