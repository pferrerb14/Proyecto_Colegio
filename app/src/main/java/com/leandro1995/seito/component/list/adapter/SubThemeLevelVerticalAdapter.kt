package com.leandro1995.seito.component.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.adapter.viewholder.SubThemeLevelVerticalViewHolder
import com.leandro1995.seito.component.list.config.callback.adapter.SubThemeLevelVerticalAdapterCallBack
import com.leandro1995.seito.component.list.config.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.component.list.model.Level
import com.leandro1995.seito.databinding.ItemSubThemeLevelVerticalBinding

class SubThemeLevelVerticalAdapter(private val levelArrayList: ArrayList<Level>) :
    RecyclerView.Adapter<SubThemeLevelVerticalViewHolder>(), ListAmbientOnclick {

    var subThemeLevelVerticalAdapterCallBack: SubThemeLevelVerticalAdapterCallBack? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SubThemeLevelVerticalViewHolder {
        return SubThemeLevelVerticalViewHolder(
            itemSubThemeLevelVerticalBinding = ItemSubThemeLevelVerticalBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), listAmbientOnclick = this
        )
    }

    override fun onBindViewHolder(
        holder: SubThemeLevelVerticalViewHolder, position: Int
    ) {
        holder.itemSubThemeLevelVerticalBinding.apply {
            titleText.text = levelArrayList[position].name
        }
    }

    override fun getItemCount(): Int {
        return levelArrayList.size
    }

    override fun onclick(position: Int) {
        subThemeLevelVerticalAdapterCallBack?.level(level = levelArrayList[position])
    }
}