package com.leandro1995.seito.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.databinding.ItemOptionBinding

class OptionViewHolder(
    val itemOptionBinding: ItemOptionBinding, private val listAmbientOnclick: ListAmbientOnclick?
) : RecyclerView.ViewHolder(itemOptionBinding.root) {
    init {
        itemOptionBinding.optionRadioButton.setOnClickListener {
            listAmbientOnclick?.onclick(bindingAdapterPosition)
        }
    }
}