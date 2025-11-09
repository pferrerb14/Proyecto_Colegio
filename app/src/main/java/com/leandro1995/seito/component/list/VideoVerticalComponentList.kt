package com.leandro1995.seito.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.VideoVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.callback.VideoVerticalComponentListCallBack
import com.leandro1995.seito.component.list.callback.adapter.VideoVerticalAdapterCallBack
import com.leandro1995.seito.component.list.model.Course

class VideoVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs), VideoVerticalAdapterCallBack {

    private var courseArrayList: ArrayList<Course>? = null
    private var videoVerticalAdapter: VideoVerticalAdapter? = null

    var videoVerticalComponentListCallBack: VideoVerticalComponentListCallBack? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        courseArrayList = arrayListOf()
        courseArrayList?.let {
            videoVerticalAdapter = VideoVerticalAdapter(courseArrayList = it).apply {
                videoVerticalAdapterCallBack = this@VideoVerticalComponentList
            }
        }

        videoVerticalAdapter?.let {
            recyclerViewLayout(recyclerViewAdapter = it)
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

        videoVerticalAdapter?.notifyDataSetChanged()
    }

    override fun videoUrl(videoUrl: String) {
        videoVerticalComponentListCallBack?.videoUrl(videoUrl = videoUrl)
    }
}