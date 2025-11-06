package com.leandro1995.seito.component.list.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.databinding.ItemCourseGridBinding

class CourseGridViewHolder(
    val itemCourseGridBinding: ItemCourseGridBinding, val listAmbientOnclick: ListAmbientOnclick?
) : RecyclerView.ViewHolder(itemCourseGridBinding.root) {

    init {
        itemCourseGridBinding.courseMaterialCardView.setOnClickListener {
            listAmbientOnclick?.onclick(bindingAdapterPosition)
        }
    }
}