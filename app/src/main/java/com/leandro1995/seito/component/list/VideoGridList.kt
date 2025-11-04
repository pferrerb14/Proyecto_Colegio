package com.leandro1995.seito.component.list

import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.VideoGridAdapter
import com.leandro1995.seito.component.list.ambient.ListAmbient

class VideoGridList(context: Context, attrs: AttributeSet? = null) : ListAmbient(context, attrs) {

    private var videoGridAdapter: VideoGridAdapter? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        videoGridAdapter = VideoGridAdapter()

        videoGridAdapter?.let {
            gridViewLayout(recyclerViewAdapter = it, spanCount = SPAN_COUNT)
        }
    }

    override fun setAdapter(arrayList: ArrayList<*>) {
        messageErrorVisibility(arrayList = arrayList)
    }

    companion object {
        private const val SPAN_COUNT = 2
    }
}