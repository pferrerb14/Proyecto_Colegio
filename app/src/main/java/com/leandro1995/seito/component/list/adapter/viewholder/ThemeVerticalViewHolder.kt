package com.leandro1995.seito.component.list.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.databinding.ItemThemeVerticalBinding

class ThemeVerticalViewHolder(
    val itemThemeVerticalBinding: ItemThemeVerticalBinding,
    private val listAmbientOnclick: ListAmbientOnclick?
) : RecyclerView.ViewHolder(itemThemeVerticalBinding.root) {

}