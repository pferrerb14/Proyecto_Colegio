package com.leandro1995.seito.component.list.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.config.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.databinding.ItemSelectQuestionVerticalBinding

class SelectQuestionVerticalViewHolder(
    val itemSelectQuestionVerticalBinding: ItemSelectQuestionVerticalBinding,
    private val listAmbientOnclick: ListAmbientOnclick?
) : RecyclerView.ViewHolder(itemSelectQuestionVerticalBinding.root) {

    init {
        itemSelectQuestionVerticalBinding.questionCheckBox.setOnClickListener {
            listAmbientOnclick?.onclick(position = bindingAdapterPosition)
        }
    }
}