package com.leandro1995.seito.component.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.adapter.viewholder.QuestionVerticalViewHolder
import com.leandro1995.seito.component.list.config.callback.adapter.QuestionVerticalAdapterCallBack
import com.leandro1995.seito.component.list.config.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.component.list.model.Question
import com.leandro1995.seito.databinding.ItemQuestionVerticalBinding

class QuestionVerticalAdapter(private val questionArrayList: ArrayList<Question>) :
    RecyclerView.Adapter<QuestionVerticalViewHolder>(), ListAmbientOnclick {

    var questionVerticalAdapterCallBack: QuestionVerticalAdapterCallBack? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): QuestionVerticalViewHolder {
        return QuestionVerticalViewHolder(
            itemQuestionVerticalBinding = ItemQuestionVerticalBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), listAmbientOnclick = this
        )
    }

    override fun onBindViewHolder(
        holder: QuestionVerticalViewHolder, position: Int
    ) {
        holder.itemQuestionVerticalBinding.apply {
            questionTitleText.text = questionArrayList[position].name
            questionAnswerText.text = questionArrayList[position].answer
            questionCoinsText.text = questionArrayList[position].coins.toString()
        }
    }

    override fun getItemCount(): Int {
        return questionArrayList.size
    }

    override fun onclick(position: Int) {
        questionVerticalAdapterCallBack?.question(question = questionArrayList[position])
    }
}