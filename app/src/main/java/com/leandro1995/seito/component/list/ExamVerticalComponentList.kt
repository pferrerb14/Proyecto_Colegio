package com.leandro1995.seito.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.ExamVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.config.callback.ExamVerticalComponentListCallBack
import com.leandro1995.seito.component.list.config.callback.adapter.ExamVerticalAdapterCallBack
import com.leandro1995.seito.component.list.model.Exam

class ExamVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs), ExamVerticalAdapterCallBack {

    private var examArrayList: ArrayList<Exam>? = null
    private var examVerticalAdapter: ExamVerticalAdapter? = null
    var examVerticalComponentListCallBack: ExamVerticalComponentListCallBack? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        examArrayList = arrayListOf()
        examVerticalAdapter = examArrayList?.let {
            ExamVerticalAdapter(it).apply {
                examDeleteVerticalCallBack = this@ExamVerticalComponentList
            }
        }

        examVerticalAdapter?.let {
            recyclerViewLayout(recyclerViewAdapter = it)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setAdapter(arrayList: ArrayList<*>) {
        messageErrorVisibility(arrayList = arrayList)

        examArrayList?.clear()
        arrayList.forEach {
            (it as com.leandro1995.seito.model.entity.Exam).let { exam ->
                examArrayList?.add(Exam(id = exam.id, name = exam.name))
            }
        }

        examVerticalAdapter?.notifyDataSetChanged()
    }

    override fun exam(exam: Exam) {
        examVerticalComponentListCallBack?.exam(
            exam = com.leandro1995.seito.model.entity.Exam(
                id = exam.id, name = exam.name
            )
        )
    }
}