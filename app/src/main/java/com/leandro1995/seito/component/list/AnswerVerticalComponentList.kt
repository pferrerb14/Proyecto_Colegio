package com.leandro1995.seito.component.list

import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.AnswerVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.model.Answer

class AnswerVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs) {

    private var answerArrayList: ArrayList<Answer>? = null
    private var answerVerticalAdapter: AnswerVerticalAdapter? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        answerArrayList = arrayListOf()
        answerVerticalAdapter =
            answerArrayList?.let { AnswerVerticalAdapter(context = context, answerArrayList = it) }

        answerVerticalAdapter?.let {
            recyclerViewLayout(recyclerViewAdapter = it)
        }
    }

    override fun setAdapter(arrayList: ArrayList<*>) {
        messageErrorVisibility(arrayList = arrayList)

        answerArrayList?.clear()
        arrayList.forEach {
            (it as com.leandro1995.seito.model.entity.Answer).let { answer ->
                answerArrayList?.add(
                    Answer(
                        imageUrl = answer.imageUrl,
                        isAnswer = answer.isAnswer,
                        name = answer.name,
                        answer = answer.answer
                    )
                )
            }
        }
    }
}