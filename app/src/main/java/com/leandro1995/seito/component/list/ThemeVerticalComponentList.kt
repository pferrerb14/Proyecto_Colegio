package com.leandro1995.seito.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.ThemeVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.callback.ThemeVerticalComponentListCallBack
import com.leandro1995.seito.component.list.callback.adapter.ThemeVerticalAdapterCallBack
import com.leandro1995.seito.component.list.model.Theme

class ThemeVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs), ThemeVerticalAdapterCallBack {

    private var themeArrayList: ArrayList<Theme>? = null
    private var themeVerticalAdapter: ThemeVerticalAdapter? = null

    var themeVerticalComponentListCallBack: ThemeVerticalComponentListCallBack? = null

    init {
        onCreateViewList()
    }


    override fun onCreateViewList() {
        themeArrayList = arrayListOf()
        themeVerticalAdapter = themeArrayList?.let {
            ThemeVerticalAdapter(it).apply {
                themeVerticalAdapterCallBack = this@ThemeVerticalComponentList
            }
        }

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
                themeArrayList?.add(Theme(id = theme.id, name = theme.name))
            }
        }

        themeVerticalAdapter?.notifyDataSetChanged()
    }

    override fun theme(theme: Theme) {
        themeVerticalComponentListCallBack?.theme(
            theme = com.leandro1995.seito.model.entity.Theme(
                id = theme.id, name = theme.name
            )
        )
    }
}