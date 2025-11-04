package com.leandro1995.seito.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.VideoGridAdapter
import com.leandro1995.seito.component.list.ambient.ListAmbient
import com.leandro1995.seito.component.list.model.Course

class VideoGridList(context: Context, attrs: AttributeSet? = null) : ListAmbient(context, attrs) {

    private var videoGridAdapter: VideoGridAdapter? = null
    private var courseArrayList: ArrayList<Course>? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        courseArrayList = arrayListOf()
        videoGridAdapter = courseArrayList?.let { VideoGridAdapter(courseArrayList = it) }

        videoGridAdapter?.let {
            gridViewLayout(recyclerViewAdapter = it, spanCount = SPAN_COUNT)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setAdapter(arrayList: ArrayList<*>) {
        messageErrorVisibility(arrayList = arrayList)

        courseArrayList?.clear()
        arrayList.forEach {
            (it as com.leandro1995.seito.model.entity.Course).let { course ->
                courseArrayList?.add(
                    Course(
                        name = course.name, imageUrl = course.imageUrl, videoUrl = course.videoUrl
                    )
                )
            }
        }

        videoGridAdapter?.notifyDataSetChanged()
    }

    companion object {
        private const val SPAN_COUNT = 2
    }
}