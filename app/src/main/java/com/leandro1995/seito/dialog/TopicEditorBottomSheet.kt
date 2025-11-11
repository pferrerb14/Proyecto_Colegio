package com.leandro1995.seito.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.leandro1995.seito.R
import com.leandro1995.seito.databinding.DialogBottomSheetTopicEditorBinding
import com.leandro1995.seito.extension.bindingFragment

class TopicEditorBottomSheet() : BottomSheetDialogFragment() {

    private var binding: DialogBottomSheetTopicEditorBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = bindingFragment(
            inflater = inflater,
            idLayout = R.layout.dialog_bottom_sheet_topic_editor,
            container = container
        )

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }
}