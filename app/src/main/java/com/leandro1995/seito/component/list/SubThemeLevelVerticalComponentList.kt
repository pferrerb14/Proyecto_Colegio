package com.leandro1995.seito.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.SubThemeLevelVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.config.callback.SubThemeLevelVerticalComponentListCallBack
import com.leandro1995.seito.component.list.config.callback.adapter.SubThemeLevelVerticalAdapterCallBack
import com.leandro1995.seito.component.list.model.Level

class SubThemeLevelVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs), SubThemeLevelVerticalAdapterCallBack {

    private var levelArrayList: ArrayList<Level>? = null
    private var subThemeLevelVerticalAdapter: SubThemeLevelVerticalAdapter? = null

    var subThemeLevelVerticalComponentListCallBack: SubThemeLevelVerticalComponentListCallBack? =
        null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        levelArrayList = arrayListOf()
        subThemeLevelVerticalAdapter = levelArrayList?.let {
            SubThemeLevelVerticalAdapter(levelArrayList = it).apply {
                subThemeLevelVerticalAdapterCallBack = this@SubThemeLevelVerticalComponentList
            }
        }

        subThemeLevelVerticalAdapter?.let {
            recyclerViewLayout(recyclerViewAdapter = it)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setAdapter(arrayList: ArrayList<*>) {
        messageErrorVisibility(arrayList = arrayList)

        levelArrayList?.clear()
        arrayList.forEach {
            (it as com.leandro1995.seito.model.entity.Level).let { level ->
                levelArrayList?.add(Level(id = level.id, name = level.name))
            }
        }

        subThemeLevelVerticalAdapter?.notifyDataSetChanged()
    }

    override fun level(level: Level) {
        subThemeLevelVerticalComponentListCallBack?.level(
            level = com.leandro1995.seito.model.entity.Level(
                id = level.id, name = level.name
            )
        )
    }
}