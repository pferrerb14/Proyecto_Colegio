package com.leandro1995.seito.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.QuestionVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.callback.QuestionVerticalComponentListCallBack
import com.leandro1995.seito.component.list.callback.adapter.QuestionVerticalAdapterCallBack
import com.leandro1995.seito.component.list.model.Question

class QuestionVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs), QuestionVerticalAdapterCallBack {

    private var questionArrayList: ArrayList<Question>? = null
    private var questionVerticalAdapter: QuestionVerticalAdapter? = null

    var questionVerticalComponentListCallBack: QuestionVerticalComponentListCallBack? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        questionArrayList = arrayListOf()
        questionVerticalAdapter = questionArrayList?.let {
            QuestionVerticalAdapter(questionArrayList = it).apply {
                questionVerticalAdapterCallBack = this@QuestionVerticalComponentList
            }
        }

        questionVerticalAdapter?.let {
            recyclerViewLayout(recyclerViewAdapter = it)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setAdapter(arrayList: ArrayList<*>) {
        messageErrorVisibility(arrayList = arrayList)

        questionArrayList?.clear()
        arrayList.forEach {
            (it as com.leandro1995.seito.model.entity.Question).let { question ->
                questionArrayList?.add(
                    Question(
                        id = question.id,
                        name = question.name,
                        answer = question.answer,
                        coins = question.coins
                    )
                )
            }
        }

        questionVerticalAdapter?.notifyDataSetChanged()
    }

    override fun question(question: Question) {
        questionVerticalComponentListCallBack?.question(
            question = com.leandro1995.seito.model.entity.Question(
                id = question.id,
                name = question.name,
                answer = question.answer,
                coins = question.coins
            )
        )
    }
}