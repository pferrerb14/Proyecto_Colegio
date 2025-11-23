package com.leandro1995.seito.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.adapter.viewholder.OptionViewHolder
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.databinding.ItemOptionBinding
import com.leandro1995.seito.model.entity.Option

class OptionAdapter(private val optionArrayList: ArrayList<Option>) :
    RecyclerView.Adapter<OptionViewHolder>(), ListAmbientOnclick {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): OptionViewHolder {
        return OptionViewHolder(
            itemOptionBinding = ItemOptionBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), listAmbientOnclick = this
        )
    }

    override fun onBindViewHolder(
        holder: OptionViewHolder, position: Int
    ) {
        holder.itemOptionBinding.apply {
            optionRadioButton.text = optionArrayList[position].name
            optionRadioButton.isChecked = optionArrayList[position].isAnswer
        }
    }

    override fun getItemCount(): Int {
        return optionArrayList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onclick(position: Int) {
        optionArrayList.forEach { it.isAnswer = false }
        optionArrayList[position].isAnswer = true
        notifyDataSetChanged()
    }
}