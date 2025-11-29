package com.leandro1995.seito.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import com.leandro1995.seito.component.list.adapter.SelectQuestionVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.callback.adapter.SelectQuestionVerticalAdapterCallBack
import com.leandro1995.seito.component.list.model.Question

class SelectQuestionVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs), SelectQuestionVerticalAdapterCallBack {
    private var questionArrayList: ArrayList<Question>? = null
    private var selectQuestionVerticalAdapter: SelectQuestionVerticalAdapter? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        questionArrayList = arrayListOf()
        selectQuestionVerticalAdapter = questionArrayList?.let {
            messageErrorVisibility(arrayList = it)
            SelectQuestionVerticalAdapter(it).apply {
                selectQuestionVerticalAdapterCallBack = this@SelectQuestionVerticalComponentList
            }
        }

        selectQuestionVerticalAdapter?.let {
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
                        id = question.id, name = question.name, answer = question.answer
                    )
                )
            }
        }

        selectQuestionVerticalAdapter?.notifyDataSetChanged()
    }

    override fun questionSelectArray(questionArrayList: ArrayList<Question>) {
        Log.e("ENTRAAAAAAA", "${questionArrayList.size}")
    }
}