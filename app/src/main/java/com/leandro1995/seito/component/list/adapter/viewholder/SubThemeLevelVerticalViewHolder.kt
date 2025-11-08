package com.leandro1995.seito.component.list.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.databinding.ItemSubThemeLevelVerticalBinding

class SubThemeLevelVerticalViewHolder(
    val itemSubThemeLevelVerticalBinding: ItemSubThemeLevelVerticalBinding,
    private val listAmbientOnclick: ListAmbientOnclick?
) : RecyclerView.ViewHolder(itemSubThemeLevelVerticalBinding.root) {

    init {
        itemSubThemeLevelVerticalBinding.themeMaterialCardView.setOnClickListener {
            listAmbientOnclick?.onclick(bindingAdapterPosition)
        }
    }
}