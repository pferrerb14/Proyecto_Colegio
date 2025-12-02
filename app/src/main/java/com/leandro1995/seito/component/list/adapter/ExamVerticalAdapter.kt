package com.leandro1995.seito.component.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.adapter.viewholder.ExamVerticalViewHolder
import com.leandro1995.seito.component.list.config.callback.adapter.ExamVerticalAdapterCallBack
import com.leandro1995.seito.component.list.config.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.component.list.model.Exam
import com.leandro1995.seito.databinding.ItemExamVerticalBinding

class ExamVerticalAdapter(private val examArrayList: ArrayList<Exam>) :
    RecyclerView.Adapter<ExamVerticalViewHolder>(), ListAmbientOnclick {

    var examDeleteVerticalCallBack: ExamVerticalAdapterCallBack? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ExamVerticalViewHolder {
        return ExamVerticalViewHolder(
            itemExamVerticalBinding = ItemExamVerticalBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), listAmbientOnclick = this
        )
    }

    override fun onBindViewHolder(
        holder: ExamVerticalViewHolder, position: Int
    ) {
        holder.itemExamVerticalBinding.apply {
            titleText.text = examArrayList[position].name
        }
    }

    override fun getItemCount(): Int {
        return examArrayList.size
    }

    override fun onclick(position: Int) {
        examDeleteVerticalCallBack?.exam(exam = examArrayList[position])
    }
}