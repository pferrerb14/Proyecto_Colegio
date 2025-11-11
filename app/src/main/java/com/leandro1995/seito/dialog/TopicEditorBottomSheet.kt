package com.leandro1995.seito.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.leandro1995.seito.R
import com.leandro1995.seito.databinding.DialogBottomSheetTopicEditorBinding
import com.leandro1995.seito.dialog.config.callback.TopicEditorBottomSheetCallBack
import com.leandro1995.seito.extension.bindingFragment

class TopicEditorBottomSheet() : BottomSheetDialogFragment() {

    private var dataBinding: DialogBottomSheetTopicEditorBinding? = null
    var topicEditorBottomSheetCallBack: TopicEditorBottomSheetCallBack? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dataBinding = bindingFragment(
            inflater = inflater,
            idLayout = R.layout.dialog_bottom_sheet_topic_editor,
            container = container
        )

        return dataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        dataBinding?.apply {
            registerButton.setOnClickListener {
                topicEditorBottomSheetCallBack?.nameTheme(name = nameEdit.text.toString())
                dismiss()
            }
        }
    }
}