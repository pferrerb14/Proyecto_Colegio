package com.leandro1995.seito.component.initial

import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.R
import com.leandro1995.seito.component.ambient.ComponentAmbient
import com.leandro1995.seito.databinding.ComponentInitialBinding

class InitialComponent(context: Context, attrs: AttributeSet? = null) :
    ComponentAmbient<ComponentInitialBinding>(context, attrs) {

    override var idLayout: Int = R.layout.component_initial

    init {
        onCreateView()
    }

    fun setText(text: String) {
        dataBinding?.initialText?.text = textInitials(text = text)
    }

    private fun textInitials(text: String): String {
        if (text.isEmpty()) {
            return ""
        }

        val initials = text.split(" ").mapNotNull { it.firstOrNull()?.toString() }
            .reduce { acc, string -> acc + string }

        return if (initials.length == 3) {
            initials.substring(0, 2)
        } else {
            initials
        }
    }
}