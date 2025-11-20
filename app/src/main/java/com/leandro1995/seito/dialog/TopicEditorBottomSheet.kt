package com.leandro1995.seito.dialog

import android.os.Bundle
import android.view.View
import com.leandro1995.seito.R
import com.leandro1995.seito.databinding.DialogBottomSheetTopicEditorBinding
import com.leandro1995.seito.dialog.ambient.BottomSheetDialogAmbient
import com.leandro1995.seito.dialog.config.callback.TopicEditorBottomSheetCallBack

class TopicEditorBottomSheet() : BottomSheetDialogAmbient<DialogBottomSheetTopicEditorBinding>() {

    var topicEditorBottomSheetCallBack: TopicEditorBottomSheetCallBack? = null

    override var idLayout: Int = R.layout.dialog_bottom_sheet_topic_editor

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding?.apply {
            registerButton.setOnClickListener {
                topicEditorBottomSheetCallBack?.nameTheme(name = nameEdit.text.toString())
                dismiss()
            }
        }
    }
}