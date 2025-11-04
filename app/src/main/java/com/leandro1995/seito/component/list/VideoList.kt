package com.leandro1995.seito.component.list

import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.ambient.ListAmbient

class VideoList(context: Context, attrs: AttributeSet? = null) : ListAmbient(context, attrs) {

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        messageErrorVisibility(arrayList = arrayListOf<String>())
    }

    override fun setAdapter(arrayList: ArrayList<*>) {
        messageErrorVisibility(arrayList = arrayList)
    }
}