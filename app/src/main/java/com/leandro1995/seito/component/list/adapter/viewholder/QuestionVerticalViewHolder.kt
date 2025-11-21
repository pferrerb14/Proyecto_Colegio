package com.leandro1995.seito.component.list.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.databinding.ItemQuestionVerticalBinding

class QuestionVerticalViewHolder(
    val itemQuestionVerticalBinding: ItemQuestionVerticalBinding,
    private val listAmbientOnclick: ListAmbientOnclick
) : RecyclerView.ViewHolder(itemQuestionVerticalBinding.root) {

    init {
        itemQuestionVerticalBinding.questionRemoveImage.setOnClickListener {
            listAmbientOnclick.onclick(position = bindingAdapterPosition)
        }
    }
}