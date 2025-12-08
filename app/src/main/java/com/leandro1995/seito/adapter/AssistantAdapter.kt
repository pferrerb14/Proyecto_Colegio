package com.leandro1995.seito.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.adapter.viewholder.AssistantAnswerViewHolder
import com.leandro1995.seito.adapter.viewholder.AssistantLoadingViewHolder
import com.leandro1995.seito.adapter.viewholder.AssistantUserViewHolder
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.ItemAnswerBinding
import com.leandro1995.seito.databinding.ItemLoginBinding
import com.leandro1995.seito.databinding.ItemUserBinding
import com.leandro1995.seito.model.entity.Chat

class AssistantAdapter(private val chatArrayList: ArrayList<Chat>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            Setting.ANSWER_CHAT -> {
                AssistantAnswerViewHolder(
                    itemAnswerBinding = ItemAnswerBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }

            Setting.USER_CHAT -> {
                AssistantUserViewHolder(
                    itemUserBinding = ItemUserBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }

            Setting.LOADING_CHAT -> {
                AssistantLoadingViewHolder(
                    itemLoginBinding = ItemLoginBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }

            else -> {
                AssistantAnswerViewHolder(
                    itemAnswerBinding = ItemAnswerBinding.inflate(
                        LayoutInflater.from(
                            parent.context
                        ), parent, false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder, position: Int
    ) {
        when (holder) {
            is AssistantAnswerViewHolder -> {
                holder.itemAnswerBinding.answerText.text = chatArrayList[position].message
            }

            is AssistantUserViewHolder -> {
                holder.itemUserBinding.userText.text = chatArrayList[position].message
            }

            is AssistantLoadingViewHolder -> {

            }
        }
    }

    override fun getItemCount(): Int {
        return chatArrayList.size
    }

    override fun getItemViewType(position: Int): Int {
        return chatArrayList[position].type
    }
}