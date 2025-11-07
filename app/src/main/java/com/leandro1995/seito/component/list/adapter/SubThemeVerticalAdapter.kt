package com.leandro1995.seito.component.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.adapter.viewholder.SubThemeVerticalViewHolder
import com.leandro1995.seito.component.list.callback.adapter.SubThemeVerticalAdapterCallBack
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.component.list.model.SubTheme
import com.leandro1995.seito.databinding.ItemSubThemeVerticalBinding

class SubThemeVerticalAdapter(private val subThemeArrayList: ArrayList<SubTheme>) :
    RecyclerView.Adapter<SubThemeVerticalViewHolder>(), ListAmbientOnclick {

    var subThemeVerticalAdapterCallBack: SubThemeVerticalAdapterCallBack? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SubThemeVerticalViewHolder {
        return SubThemeVerticalViewHolder(
            itemSubThemeVerticalBinding = ItemSubThemeVerticalBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), listAmbientOnclick = this
        )
    }

    override fun onBindViewHolder(
        holder: SubThemeVerticalViewHolder, position: Int
    ) {
        holder.itemSubThemeVerticalBinding.apply {
            titleText.text = subThemeArrayList[position].name
        }
    }

    override fun getItemCount(): Int {
        return subThemeArrayList.size
    }

    override fun onclick(position: Int) {
        subThemeVerticalAdapterCallBack?.subTheme(subTheme = subThemeArrayList[position])
    }
}