package com.leandro1995.seito.component.list.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.adapter.viewholder.SelectQuestionVerticalViewHolder
import com.leandro1995.seito.component.list.callback.adapter.SelectQuestionVerticalAdapterCallBack
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.component.list.model.Question
import com.leandro1995.seito.databinding.ItemSelectQuestionVerticalBindingImpl

class SelectQuestionVerticalAdapter(private val questionArrayList: ArrayList<Question>) :
    RecyclerView.Adapter<SelectQuestionVerticalViewHolder>(), ListAmbientOnclick {

    var selectQuestionVerticalAdapterCallBack: SelectQuestionVerticalAdapterCallBack? = null

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
            questionCheckBox.apply {
                isChecked = questionArrayList[position].checked
                isEnabled = questionArrayList[position].isEnable
            }
            questionTitleText.text = questionArrayList[position].name
            questionAnswerText.text = questionArrayList[position].answer
        }
    }

    override fun getItemCount(): Int {
        return questionArrayList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onclick(position: Int) {

        questionArrayList.let {
            it.forEach { question -> question.isEnable = true }

            it[position].let { question ->
                if (question.checked) {
                    question.checked = false
                } else {
                    (it.filter { question -> question.checked }.size == 5).let { value ->
                        question.checked = !value

                        if (value) {
                            it.filter { question -> !question.checked }.forEach { question ->
                                question.isEnable = false
                            }
                        }
                    }
                }
            }
        }
        notifyDataSetChanged()
    }
}