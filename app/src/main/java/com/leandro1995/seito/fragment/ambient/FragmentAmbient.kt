package com.leandro1995.seito.fragment.ambient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.leandro1995.seito.extension.bindingFragment

abstract class FragmentAmbient<binding : ViewDataBinding> : Fragment() {

    protected var dataBinding: binding? = null

    protected abstract var idLayout: Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        initEventToAction()
        return contentView(inflater = inflater, container = container)?.root
    }

    private fun contentView(inflater: LayoutInflater, container: ViewGroup?): binding? {
        if (idLayout != -1) {
            if (dataBinding == null) {
                dataBinding = bindingFragment(
                    inflater = inflater, idLayout = idLayout, container = container
                )
            }
        }
        return dataBinding
    }

    open fun initEventToAction() {}
}