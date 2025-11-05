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
                courseArrayList?.add(
                    Course(
                        id = course.id,
                        name = course.name,
                        themeArrayList = modelThemeArrayList(courseThemeArrayList = course.themeArrayList)
                    )
                )
            }
        }

        courseGridAdapter?.notifyDataSetChanged()
    }

    override fun themeArrayList(themeArrayList: ArrayList<Theme>) {
        courseGridComponentListCallBack?.themeArrayList(entityThemeArrayList(modelThemeArrayList = themeArrayList))
    }

    private fun modelThemeArrayList(courseThemeArrayList: ArrayList<com.leandro1995.seito.model.entity.Theme>): ArrayList<Theme> {
        val themeArrayList = arrayListOf<Theme>()

        courseThemeArrayList.forEach {
            themeArrayList.add(Theme(name = it.name))
        }

        return themeArrayList
    }

    private fun entityThemeArrayList(modelThemeArrayList: ArrayList<Theme>): ArrayList<com.leandro1995.seito.model.entity.Theme> {
        val themeArrayList = arrayListOf<com.leandro1995.seito.model.entity.Theme>()

        modelThemeArrayList.forEach {
            themeArrayList.add(com.leandro1995.seito.model.entity.Theme(name = it.name))
        }

        return themeArrayList
    }

    companion object {
        private const val SPAN_COUNT = 2
    }
}