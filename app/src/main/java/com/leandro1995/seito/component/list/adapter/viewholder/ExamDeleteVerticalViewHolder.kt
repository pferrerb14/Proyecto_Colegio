package com.leandro1995.seito.component.list.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.config.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.databinding.ItemExamDeleteVerticalBinding

class ExamDeleteVerticalViewHolder(
    val itemExamDeleteVerticalBinding: ItemExamDeleteVerticalBinding, val listAmbientOnclick: ListAmbientOnclick
) : RecyclerView.ViewHolder(itemExamDeleteVerticalBinding.root) {

    init {
        itemExamDeleteVerticalBinding.examRemoveImage.setOnClickListener {
            listAmbientOnclick.onclick(position = bindingAdapterPosition)
        }
    }
}