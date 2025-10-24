package com.leandro1995.seito.util.design

import android.content.Context
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.leandro1995.seito.R

class LoginUtilDesign {

    companion object {
        fun selectType(context: Context, active: TextView?, vararg deactivated: TextView?) {
            active?.let {
                it.background =
                    ContextCompat.getDrawable(context, R.drawable.background_select_user_type)
                it.setTextColor(ContextCompat.getColor(context, R.color.white_FFFFFF))
            }

            deactivated.let {
                it.forEach { textView ->
                    textView?.background = null
                    textView?.setTextColor(ContextCompat.getColor(context, R.color.black_000000))
                }
            }
        }
    }
}