package com.leandro1995.seito.component.list.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.databinding.ItemOptionQuestionBinding

class OptionVerticalHolder(val itemOptionQuestionBinding: ItemOptionQuestionBinding,val listAmbientOnclick: ListAmbientOnclick) :
    RecyclerView.ViewHolder(itemOptionQuestionBinding.root)