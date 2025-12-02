package com.leandro1995.seito.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.ExamDeleteVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.config.callback.ExamDeleteVerticalComponentListCallBack
import com.leandro1995.seito.component.list.config.callback.adapter.ExamDeleteVerticalCallBack
import com.leandro1995.seito.component.list.model.Exam

class ExamDeleteDeleteVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs), ExamDeleteVerticalCallBack {

    private var examArrayList: ArrayList<Exam>? = null

    private var examDeleteVerticalAdapter: ExamDeleteVerticalAdapter? = null

    var examDeleteVerticalComponentListCallBack: ExamDeleteVerticalComponentListCallBack? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        examArrayList = arrayListOf()
        examDeleteVerticalAdapter = examArrayList?.let {
            ExamDeleteVerticalAdapter(examArrayList = it).apply {
                examDeleteVerticalCallBack = this@ExamDeleteDeleteVerticalComponentList
            }
        }

        examDeleteVerticalAdapter?.let {
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

        examDeleteVerticalAdapter?.notifyDataSetChanged()
    }

    override fun idExam(id: String) {
        examDeleteVerticalComponentListCallBack?.idExam(id = id)
    }
}