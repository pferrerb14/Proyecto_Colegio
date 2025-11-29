package com.leandro1995.seito.component.list.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.config.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.databinding.ItemStudentVerticalBinding

class StudentVerticalViewHolder(
    val itemStudentVerticalBinding: ItemStudentVerticalBinding,
    val listAmbientOnclick: ListAmbientOnclick?
) : RecyclerView.ViewHolder(itemStudentVerticalBinding.root) {

    init {
        itemStudentVerticalBinding.studentMaterialCardView.setOnClickListener {
            listAmbientOnclick?.onclick(position = bindingAdapterPosition)
        }
    }
}