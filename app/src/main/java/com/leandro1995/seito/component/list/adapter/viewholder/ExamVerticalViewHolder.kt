package com.leandro1995.seito.component.list.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.config.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.databinding.ItemExamVerticalBinding

class ExamVerticalViewHolder(
    val itemExamVerticalBinding: ItemExamVerticalBinding,
    private val listAmbientOnclick: ListAmbientOnclick
) : RecyclerView.ViewHolder(itemExamVerticalBinding.root) {

    init {
        itemExamVerticalBinding.examMaterialCardView.setOnClickListener {
            listAmbientOnclick.onclick(bindingAdapterPosition)
        }
    }
}