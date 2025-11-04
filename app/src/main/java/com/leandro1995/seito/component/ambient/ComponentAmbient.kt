package com.leandro1995.seito.component.ambient

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.StyleableRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.ViewDataBinding
import com.leandro1995.seito.extension.binding

abstract class ComponentAmbient<binding : ViewDataBinding> @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs) {

    protected var dataBinding: binding? = null

    protected abstract var idLayout: Int

    protected fun onCreateView() {
        if (idLayout != -1) {
            if (dataBinding == null) {
                dataBinding = binding(context = context, idLayout = idLayout)
            }
        }
    }

    protected fun typeArray(
        attrs: AttributeSet? = null, @StyleableRes idStyleableRes: IntArray
    ): TypedArray? = context.obtainStyledAttributes(attrs, idStyleableRes)

    open fun visibility(isVisible: Boolean) {}

    open fun typeArrayView(typedArray: TypedArray?) {}
}