package com.leandro1995.seito.component.list.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.config.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.databinding.ItemVideoVerticalBinding

class VideoVerticalViewHolder(
    val itemVideoVerticalBinding: ItemVideoVerticalBinding,
    val listAmbientOnclick: ListAmbientOnclick
) : RecyclerView.ViewHolder(itemVideoVerticalBinding.root) {

    init {
        itemVideoVerticalBinding.videoGridMaterialCardView.setOnClickListener {
            listAmbientOnclick.onclick(bindingAdapterPosition)
        }
    }
}