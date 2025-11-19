package com.leandro1995.seito.dialog

import android.os.Bundle
import android.view.View
import com.leandro1995.seito.R
import com.leandro1995.seito.databinding.DialogBottomSheetOptionAddBinding
import com.leandro1995.seito.dialog.ambient.BottomSheetDialogAmbient
import com.leandro1995.seito.dialog.config.callback.OptionAddBottomSheetCallBack
import com.leandro1995.seito.extension.visible
import com.leandro1995.seito.model.entity.Option

class OptionAddBottomSheet(private val isVisibleAnswer: Boolean) :
    BottomSheetDialogAmbient<DialogBottomSheetOptionAddBinding>() {

    var optionAddBottomSheetCallBack: OptionAddBottomSheetCallBack? = null

    override var idLayout: Int = R.layout.dialog_bottom_sheet_option_add

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding?.apply {
            optionCheck.visibility = visible(isVisible = !isVisibleAnswer)
            registerButton.setOnClickListener {
                optionAddBottomSheetCallBack?.option(
                    option = Option(
                        isAnswer = optionCheck.isChecked, name = optionNameEdit.text.toString()
                    )
                )
                dismiss()
            }
        }
    }
}