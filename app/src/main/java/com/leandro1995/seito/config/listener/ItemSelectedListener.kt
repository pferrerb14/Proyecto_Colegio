package com.leandro1995.seito.config.listener

import android.view.View
import android.widget.AdapterView
import com.leandro1995.seito.config.callback.adapter.listener.ItemSelectedListenerCallBack

class ItemSelectedListener<T>(private val arrayList: ArrayList<T>) :
    AdapterView.OnItemSelectedListener {

    private var isInitialSelect = true

    var itemSelectedListenerCallBack: ItemSelectedListenerCallBack<T>? = null

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (!isInitialSelect) {
            itemSelectedListenerCallBack?.item(item = arrayList[position])
        } else {
            isInitialSelect = false
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}