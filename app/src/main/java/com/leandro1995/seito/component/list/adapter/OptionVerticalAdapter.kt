package com.leandro1995.seito.component.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.R
import com.leandro1995.seito.component.list.adapter.viewholder.OptionVerticalHolder
import com.leandro1995.seito.component.list.callback.adapter.OptionVerticalAdapterCallBack
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.component.list.model.Option
import com.leandro1995.seito.databinding.ItemOptionQuestionBinding

class OptionVerticalAdapter(
    private val context: Context, private val optionArrayList: ArrayList<Option>
) : RecyclerView.Adapter<OptionVerticalHolder>(), ListAmbientOnclick {

    var optionVerticalAdapterCallBack: OptionVerticalAdapterCallBack? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): OptionVerticalHolder {
        return OptionVerticalHolder(
            itemOptionQuestionBinding = ItemOptionQuestionBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), listAmbientOnclick = this
        )
    }

    override fun onBindViewHolder(
        holder: OptionVerticalHolder, position: Int
    ) {
        holder.itemOptionQuestionBinding.apply {
            optionText.text = optionArrayList[position].option
            optionMaterialCard.setCardBackgroundColor(
                if (optionArrayList[position].check) {
                    context.getColor(R.color.green_EEFBF3)
                } else {
                    context.getColor(R.color.white_FFFFFF)
                }
            )
        }
    }

    override fun getItemCount(): Int {
        return optionArrayList.size
    }

    override fun onclick(position: Int) {
        optionVerticalAdapterCallBack?.deleteOption(position = position)
    }
}