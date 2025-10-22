package com.leandro1995.seito.component.ambient

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ViewDataBinding
import com.leandro1995.seito.extension.binding

abstract class ComponentAmbient<binding : ViewDataBinding> @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    protected abstract val idLayout: Int

    init {
        onCreateView(dataBinding = (context as AppCompatActivity).binding(idLayout = idLayout))
    }

    protected abstract fun onCreateView(dataBinding: binding?)
}