package com.leandro1995.seito.component.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.adapter.viewholder.SelectQuestionVerticalViewHolder
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.component.list.model.Question
import com.leandro1995.seito.databinding.ItemSelectQuestionVerticalBindingImpl

class SelectQuestionVerticalAdapter(private val questionArrayList: ArrayList<Question>) :
    RecyclerView.Adapter<SelectQuestionVerticalViewHolder>(), ListAmbientOnclick {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SelectQuestionVerticalViewHolder {
        return SelectQuestionVerticalViewHolder(
            itemSelectQuestionVerticalBinding = ItemSelectQuestionVerticalBindingImpl.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), listAmbientOnclick = this
        )
    }

    override fun onBindViewHolder(
        holder: SelectQuestionVerticalViewHolder, position: Int
    ) {
        holder.itemSelectQuestionVerticalBinding.apply {
            questionCheckBox.isChecked = questionArrayList[position].checked
            questionTitleText.text = questionArrayList[position].name
            questionAnswerText.text = questionArrayList[position].answer
        }
    }

    override fun getItemCount(): Int {
        return questionArrayList.size
    }

    override fun onclick(position: Int) {

    }
}