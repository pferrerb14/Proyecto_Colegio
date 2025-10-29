package com.leandro1995.seito.util.design

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.leandro1995.seito.R

class StudentRegisterUtilDesign {

    companion object {
        fun sexSelect(
            context: Context,
            activeTriple: Triple<View, ImageView, LinearLayout>,
            deactivateTriple: Triple<View, ImageView, LinearLayout>,
            @DrawableRes activeIcon: Int,
            @DrawableRes deactivateIcon: Int
        ) {
            activeTriple.let {
                it.first.background =
                    ContextCompat.getDrawable(context, R.drawable.background_black_spot)
                it.second.setImageResource(activeIcon)
                it.third.background =
                    ContextCompat.getDrawable(context, R.drawable.background_sex_selected)
            }

            deactivateTriple.let {
                it.first.background =
                    ContextCompat.getDrawable(context, R.drawable.background_white_spot)
                it.second.setImageResource(deactivateIcon)
                it.third.background =
                    ContextCompat.getDrawable(context, R.drawable.background_sex_deselected)
            }
        }
    }
}