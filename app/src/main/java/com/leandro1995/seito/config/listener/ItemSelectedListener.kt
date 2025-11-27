package com.leandro1995.seito.config.listener

import android.view.View
import android.widget.AdapterView
import com.leandro1995.seito.config.callback.adapter.listener.ItemSelectedListenerCallBack

class ItemSelectedListener<T>(private val arrayList: ArrayList<T>) :
    AdapterView.OnItemSelectedListener {

    var itemSelectedListenerCallBack: ItemSelectedListenerCallBack<T>? = null

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        itemSelectedListenerCallBack?.item(item = arrayList[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}