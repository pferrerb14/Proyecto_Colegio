package com.leandro1995.seito.component.list

import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.OptionVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.model.Option

class OptionVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs) {

    private var optionArrayList: ArrayList<Option>? = null
    private var optionVerticalAdapter: OptionVerticalAdapter? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        optionArrayList = arrayListOf()
        optionVerticalAdapter = optionArrayList?.let {
            messageErrorVisibility(arrayList = it)
            OptionVerticalAdapter(context = context, optionArrayList = it)
        }

        optionVerticalAdapter?.let {
            recyclerViewLayout(recyclerViewAdapter = it)
        }
    }
}