package com.leandro1995.seito.component.loading

import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.R
import com.leandro1995.seito.component.ambient.ComponentAmbient
import com.leandro1995.seito.databinding.ComponentLoadingBinding

class LoadingComponent(context: Context, attrs: AttributeSet? = null) :
    ComponentAmbient<ComponentLoadingBinding>(context, attrs) {

    override var idLayout: Int = R.layout.component_loading

    init {
        onCreateView()
    }

    override fun visibility(isVisible: Boolean) {
        dataBinding?.loadingConstraint?.visibility = if (isVisible) VISIBLE else GONE
    }
}