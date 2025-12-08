package com.leandro1995.seito.component.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.R
import com.leandro1995.seito.component.list.adapter.viewholder.AnswerVerticalViewHolder
import com.leandro1995.seito.component.list.model.Answer
import com.leandro1995.seito.databinding.ItemAnswerVerticalBinding

class AnswerVerticalAdapter(
    private val context: Context, private val answerArrayList: ArrayList<Answer>
) : RecyclerView.Adapter<AnswerVerticalViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): AnswerVerticalViewHolder {
        return AnswerVerticalViewHolder(
            itemAnswerVerticalBinding = ItemAnswerVerticalBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: AnswerVerticalViewHolder, position: Int
    ) {
        holder.itemAnswerVerticalBinding.apply {
            answerImageSimpleDraweeView.setImageURI(answerArrayList[position].imageUrl)
            titleText.text =
                context.getString(R.string.question_item_text, answerArrayList[position].name)
            answerText.text =
                context.getString(R.string.answer_item_text, answerArrayList[position].answer)
            stateText.let {
                it.text = if (answerArrayList[position].isAnswer) {
                    context.getString(R.string.correct_text)
                } else {
                    context.getString(R.string.incorrect_text)
                }
                it.setTextColor(
                    ContextCompat.getColor(
                        context, if (answerArrayList[position].isAnswer) {
                            R.color.green_9EE6B8
                        } else {
                            R.color.red_F92C36
                        }
                    )
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return answerArrayList.size
    }
}