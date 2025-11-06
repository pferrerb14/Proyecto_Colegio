package com.leandro1995.seito.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.ThemeVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.model.Theme

class ThemeVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs) {

    private var themeArrayList: ArrayList<Theme>? = null

    private var themeVerticalAdapter: ThemeVerticalAdapter? = null

    init {
        onCreateViewList()
    }


    override fun onCreateViewList() {
        themeArrayList = arrayListOf()
        themeVerticalAdapter = themeArrayList?.let { ThemeVerticalAdapter(it) }

        themeVerticalAdapter?.let {
            recyclerViewLayout(recyclerViewAdapter = it)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setAdapter(arrayList: ArrayList<*>) {
        messageErrorVisibility(arrayList = arrayList)

        themeArrayList?.clear()
        arrayList.forEach {
            (it as com.leandro1995.seito.model.entity.Theme).let { theme ->
                themeArrayList?.add(Theme(name = theme.name))
            }
        }

        themeVerticalAdapter?.notifyDataSetChanged()
    }
}