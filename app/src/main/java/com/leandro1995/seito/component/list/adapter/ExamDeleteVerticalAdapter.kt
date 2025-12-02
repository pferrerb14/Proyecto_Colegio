package com.leandro1995.seito.component.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.adapter.viewholder.ExamDeleteVerticalViewHolder
import com.leandro1995.seito.component.list.config.callback.adapter.ExamDeleteVerticalCallBack
import com.leandro1995.seito.component.list.config.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.component.list.model.Exam
import com.leandro1995.seito.databinding.ItemExamVerticalBinding

class ExamDeleteVerticalAdapter(private val examArrayList: ArrayList<Exam>) :
    RecyclerView.Adapter<ExamDeleteVerticalViewHolder>(), ListAmbientOnclick {

    var examDeleteVerticalCallBack: ExamDeleteVerticalCallBack? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ExamDeleteVerticalViewHolder {
        return ExamDeleteVerticalViewHolder(
            itemExamVerticalBinding = ItemExamVerticalBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), listAmbientOnclick = this
        )
    }

    override fun onBindViewHolder(
        holder: ExamDeleteVerticalViewHolder, position: Int
    ) {
        holder.itemExamVerticalBinding.apply {
            examText.text = examArrayList[position].name
        }
    }

    override fun getItemCount(): Int {
        return examArrayList.size
    }

    override fun onclick(position: Int) {
        examDeleteVerticalCallBack?.idExam(id = examArrayList[position].id)
    }
}