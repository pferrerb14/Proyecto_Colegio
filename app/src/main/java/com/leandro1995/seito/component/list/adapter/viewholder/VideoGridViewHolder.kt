package com.leandro1995.seito.component.list.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.databinding.ItemVideoGridBinding

class VideoGridViewHolder(
    val itemVideoGridBinding: ItemVideoGridBinding, val listAmbientOnclick: ListAmbientOnclick?
) : RecyclerView.ViewHolder(itemVideoGridBinding.root) {

    init {
        itemVideoGridBinding.videoGridMaterialCardView.setOnClickListener {
            listAmbientOnclick?.onclick(position = bindingAdapterPosition)
        }
    }
}