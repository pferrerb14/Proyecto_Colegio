package com.leandro1995.seito.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.ExamVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.config.callback.ExamVerticalComponentListCallBack
import com.leandro1995.seito.component.list.config.callback.adapter.ExamVerticalCallBack
import com.leandro1995.seito.component.list.model.Exam

class ExamVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs), ExamVerticalCallBack {

    private var examArrayList: ArrayList<Exam>? = null

    private var examVerticalAdapter: ExamVerticalAdapter? = null

    var examVerticalComponentListCallBack: ExamVerticalComponentListCallBack? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        examArrayList = arrayListOf()
        examVerticalAdapter = examArrayList?.let {
            ExamVerticalAdapter(examArrayList = it).apply {
                examVerticalCallBack = this@ExamVerticalComponentList
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
                examArrayList?.add(Exam(name = exam.name, id = exam.id))
            }
        }

        examVerticalAdapter?.notifyDataSetChanged()
    }

    override fun idExam(id: String) {
        examVerticalComponentListCallBack?.idExam(id = id)
    }
}