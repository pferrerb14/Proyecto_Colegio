package com.leandro1995.seito.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.CourseGridAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.callback.CourseGridComponentListCallBack
import com.leandro1995.seito.component.list.callback.adapter.CourseGridAdapterCallBack
import com.leandro1995.seito.component.list.model.Course
import com.leandro1995.seito.component.list.model.Theme

class CourseGridComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs), CourseGridAdapterCallBack {

    private var courseArrayList: ArrayList<Course>? = null
    private var courseGridAdapter: CourseGridAdapter? = null

    var courseGridComponentListCallBack: CourseGridComponentListCallBack? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        courseArrayList = arrayListOf()
        courseGridAdapter = courseArrayList?.let {
            CourseGridAdapter(courseArrayList = it).apply {
                courseGridAdapterCallBack = this@CourseGridComponentList
            }
        }

        courseGridAdapter?.let {
            gridViewLayout(recyclerViewAdapter = it, spanCount = SPAN_COUNT)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setAdapter(arrayList: ArrayList<*>) {
        messageErrorVisibility(arrayList = arrayList)

        courseArrayList?.clear()
        arrayList.forEach {
            (it as com.leandro1995.seito.model.entity.Course).let { course ->
                courseArrayList?.add(Course(id = course.id, name = course.name))
            }
        }

        courseGridAdapter?.notifyDataSetChanged()
    }

    override fun themeArrayList(themeArrayList: ArrayList<Theme>) {

    }

    companion object {
        private const val SPAN_COUNT = 2
    }
}