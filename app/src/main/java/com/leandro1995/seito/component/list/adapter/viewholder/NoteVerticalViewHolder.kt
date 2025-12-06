package com.leandro1995.seito.component.list.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.config.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.databinding.ItemNoteVerticalBinding

class NoteVerticalViewHolder(
    val itemNoteVerticalBinding: ItemNoteVerticalBinding,
    private val listAmbientOnclick: ListAmbientOnclick?
) : RecyclerView.ViewHolder(itemNoteVerticalBinding.root) {

    init {

        itemNoteVerticalBinding.noteMaterialCardView.setOnClickListener {
            listAmbientOnclick?.onclick(position = bindingAdapterPosition)
        }
    }
}