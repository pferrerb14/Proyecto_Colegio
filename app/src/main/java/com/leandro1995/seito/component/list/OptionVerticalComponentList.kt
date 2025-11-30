package com.leandro1995.seito.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.OptionVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.config.callback.OptionVerticalComponentListCallBack
import com.leandro1995.seito.component.list.config.callback.adapter.OptionVerticalAdapterCallBack
import com.leandro1995.seito.component.list.model.Option

class OptionVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs), OptionVerticalAdapterCallBack {

    private var optionArrayList: ArrayList<Option>? = null
    private var optionVerticalAdapter: OptionVerticalAdapter? = null
    var optionVerticalComponentListCallBack: OptionVerticalComponentListCallBack? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        optionArrayList = arrayListOf()
        optionVerticalAdapter = optionArrayList?.let {
            messageErrorVisibility(arrayList = it)
            OptionVerticalAdapter(context = context, optionArrayList = it).apply {
                optionVerticalAdapterCallBack = this@OptionVerticalComponentList
            }
        }

        optionVerticalAdapter?.let {
            recyclerViewLayout(recyclerViewAdapter = it)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setAdapter(arrayList: ArrayList<*>) {
        messageErrorVisibility(arrayList = arrayList)

        optionArrayList?.clear()
        arrayList.forEach {
            (it as com.leandro1995.seito.model.entity.Option).let { option ->
                optionArrayList?.add(Option(check = option.isAnswer, option = option.name))
            }
        }

        optionVerticalAdapter?.notifyDataSetChanged()
    }

    override fun deleteOption(position: Int) {
        optionVerticalComponentListCallBack?.deleteOption(position = position)
    }
}