package com.leandro1995.seito.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.SubThemeVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.config.callback.SubThemeVerticalComponentListCallBack
import com.leandro1995.seito.component.list.config.callback.adapter.SubThemeVerticalAdapterCallBack
import com.leandro1995.seito.component.list.model.SubTheme

class SubThemeVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs), SubThemeVerticalAdapterCallBack {

    private var subthemeArrayList: ArrayList<SubTheme>? = null
    private var subThemeVerticalAdapter: SubThemeVerticalAdapter? = null

    var subThemeVerticalComponentListCallBack: SubThemeVerticalComponentListCallBack? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        subthemeArrayList = arrayListOf()

        subThemeVerticalAdapter = subthemeArrayList?.let {
            SubThemeVerticalAdapter(it).apply {
                subThemeVerticalAdapterCallBack = this@SubThemeVerticalComponentList
            }
        }

        subThemeVerticalAdapter?.let {
            recyclerViewLayout(recyclerViewAdapter = it)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setAdapter(arrayList: ArrayList<*>) {
        messageErrorVisibility(arrayList = arrayList)

        subthemeArrayList?.clear()
        arrayList.forEach {
            (it as com.leandro1995.seito.model.entity.SubTheme).let { subTheme ->
                subthemeArrayList?.add(SubTheme(id = subTheme.id, name = subTheme.name))
            }
        }

        subThemeVerticalAdapter?.notifyDataSetChanged()
    }

    override fun subTheme(subTheme: SubTheme) {
        subThemeVerticalComponentListCallBack?.subTheme(
            com.leandro1995.seito.model.entity.SubTheme(
                id = subTheme.id, name = subTheme.name
            )
        )
    }
}