package com.leandro1995.seito.component.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.adapter.viewholder.CourseGridViewHolder
import com.leandro1995.seito.component.list.config.callback.adapter.CourseGridAdapterCallBack
import com.leandro1995.seito.component.list.config.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.component.list.model.Course
import com.leandro1995.seito.databinding.ItemCourseGridBinding

class CourseGridAdapter(private val courseArrayList: ArrayList<Course>) :
    RecyclerView.Adapter<CourseGridViewHolder>(), ListAmbientOnclick {

    var courseGridAdapterCallBack: CourseGridAdapterCallBack? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CourseGridViewHolder {
        return CourseGridViewHolder(
            itemCourseGridBinding = ItemCourseGridBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), listAmbientOnclick = this
        )
    }

    override fun onBindViewHolder(
        holder: CourseGridViewHolder, position: Int
    ) {
        holder.itemCourseGridBinding.apply {
            courseArrayList[position].iconCourseType()?.let {
                courseImageView.setImageResource(it)
            }
            courseNameText.text = courseArrayList[position].name
        }
    }

    override fun getItemCount(): Int {
        return courseArrayList.size
    }

    override fun onclick(position: Int) {
        courseGridAdapterCallBack?.course(course = courseArrayList[position])
    }
}