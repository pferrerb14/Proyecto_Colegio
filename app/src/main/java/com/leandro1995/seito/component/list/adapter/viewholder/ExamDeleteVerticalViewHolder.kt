package com.leandro1995.seito.component.list.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.config.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.databinding.ItemExamVerticalBinding

class ExamDeleteVerticalViewHolder(
    val itemExamVerticalBinding: ItemExamVerticalBinding, val listAmbientOnclick: ListAmbientOnclick
) : RecyclerView.ViewHolder(itemExamVerticalBinding.root) {

    init {
        itemExamVerticalBinding.examRemoveImage.setOnClickListener {
            listAmbientOnclick.onclick(position = bindingAdapterPosition)
        }
    }
}