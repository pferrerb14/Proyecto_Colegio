package com.leandro1995.seito.util.dialog

import android.content.Context
import androidx.fragment.app.FragmentManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.leandro1995.seito.R
import com.leandro1995.seito.dialog.OptionAddBottomSheet
import com.leandro1995.seito.dialog.TopicEditorBottomSheet
import com.leandro1995.seito.dialog.config.callback.OptionAddBottomSheetCallBack
import com.leandro1995.seito.dialog.config.callback.TopicEditorBottomSheetCallBack
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Option

object AppUtilDialog {

    fun dialogMaterialDesign(
        context: Context, alertMessage: AlertMessage, positiveButton: () -> Unit = {}
    ) {
        MaterialAlertDialogBuilder(context).setCancelable(alertMessage.isCancelable)
            .setTitle(context.getString(R.string.app_name))
            .setMessage(alertMessage.message(context = context))
            .setPositiveButton(context.getString(R.string.accept_button)) { dialog, which ->
                dialog.dismiss()
                positiveButton()
            }.show()
    }

    fun topicEditorBottomSheet(fragmentManager: FragmentManager, text: (String) -> Unit) {
        TopicEditorBottomSheet().apply {
            topicEditorBottomSheetCallBack = object : TopicEditorBottomSheetCallBack {
                override fun nameTheme(name: String) {
                    text(name)
                }
            }
            show(fragmentManager, TOPIC_EDITOR_BOTTOM_SHEET)
        }
    }

    fun optionAddBottomSheet(
        fragmentManager: FragmentManager, isVisibleAnswer: Boolean, option: (Option) -> Unit
    ) {
        OptionAddBottomSheet(isVisibleAnswer = isVisibleAnswer).apply {
            optionAddBottomSheetCallBack = object : OptionAddBottomSheetCallBack {
                override fun option(option: Option) {
                    option(option)
                }
            }
            show(fragmentManager, QUESTION_ADD_BOTTOM_SHEET)
        }
    }

    private const val TOPIC_EDITOR_BOTTOM_SHEET = "topicEditorBottomSheet"
    private const val QUESTION_ADD_BOTTOM_SHEET = "questionAddBottomSheet"
}