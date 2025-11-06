package com.leandro1995.seito.component.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.adapter.viewholder.ThemeVerticalViewHolder
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.component.list.model.Theme
import com.leandro1995.seito.databinding.ItemThemeVerticalBinding

class ThemeVerticalAdapter(private val themeArrayList: ArrayList<Theme>) :
    RecyclerView.Adapter<ThemeVerticalViewHolder>(), ListAmbientOnclick {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ThemeVerticalViewHolder {
        return ThemeVerticalViewHolder(
            itemThemeVerticalBinding = ItemThemeVerticalBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), listAmbientOnclick = this
        )
    }

    override fun onBindViewHolder(
        holder: ThemeVerticalViewHolder, position: Int
    ) {
        holder.itemThemeVerticalBinding.apply {
            titleText.text = themeArrayList[position].name
        }
    }

    override fun getItemCount(): Int {
        return themeArrayList.size
    }

    override fun onclick(position: Int) {

    }
}