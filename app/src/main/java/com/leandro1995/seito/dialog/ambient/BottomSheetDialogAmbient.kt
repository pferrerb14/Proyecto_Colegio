package com.leandro1995.seito.dialog.ambient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.leandro1995.seito.extension.bindingFragment

abstract class BottomSheetDialogAmbient<binding : ViewDataBinding> : BottomSheetDialogFragment() {

    protected var dataBinding: binding? = null

    protected abstract var idLayout: Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        contentView(inflater = inflater, container = container)
        return dataBinding?.root
    }

    private fun contentView(inflater: LayoutInflater, container: ViewGroup?) {
        if (idLayout != -1) {
            if (dataBinding == null) {
                dataBinding = bindingFragment(
                    inflater = inflater, idLayout = idLayout, container = container
                )
            }
        }
    }
}