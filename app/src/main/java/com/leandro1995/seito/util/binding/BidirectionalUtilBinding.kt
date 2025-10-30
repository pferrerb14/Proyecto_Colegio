package com.leandro1995.seito.util.binding

import android.widget.EditText
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter

object BidirectionalUtilBinding {

    @BindingAdapter("android:int")
    @JvmStatic
    fun setInt(editText: EditText, value: Int) {
        if (value != -1) {
            editText.setText(value.toString())
        }
    }

    @InverseBindingAdapter(attribute = "android:int", event = "android:textAttrChanged")
    @JvmStatic
    fun getInt(editText: EditText): Int {
        return if (editText.text.toString().isEmpty()) {
            -1
        } else {
            editText.text.toString().toInt()
        }
    }
}