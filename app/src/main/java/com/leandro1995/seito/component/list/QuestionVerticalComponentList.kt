package com.leandro1995.seito.component.list

import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.QuestionVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.model.Question

class QuestionVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs) {

    private var questionArrayList: ArrayList<Question>? = null
    private var questionVerticalAdapter: QuestionVerticalAdapter? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        questionArrayList = arrayListOf()
        questionVerticalAdapter =
            questionArrayList?.let { QuestionVerticalAdapter(questionArrayList = it) }

        questionVerticalAdapter?.let {
            recyclerViewLayout(recyclerViewAdapter = it)
        }
    }

    override fun setAdapter(arrayList: ArrayList<*>) {
        
    }
}