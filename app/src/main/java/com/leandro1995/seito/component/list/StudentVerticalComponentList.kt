package com.leandro1995.seito.component.list

import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient

class StudentVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs) {

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {

    }

    override fun setAdapter(arrayList: ArrayList<*>) {

    }
}