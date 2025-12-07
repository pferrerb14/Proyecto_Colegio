package com.leandro1995.seito.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.StudentVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.config.callback.StudentVerticalComponentListCallBack
import com.leandro1995.seito.component.list.config.callback.adapter.StudentVerticalAdapterCallBack
import com.leandro1995.seito.component.list.model.Student

class StudentVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs), StudentVerticalAdapterCallBack {

    private var studentArrayList: ArrayList<Student>? = null
    private var studentVerticalAdapter: StudentVerticalAdapter? = null

    var studentVerticalComponentListCallBack: StudentVerticalComponentListCallBack? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        studentArrayList = arrayListOf()
        studentVerticalAdapter = studentArrayList?.let {
            StudentVerticalAdapter(context = context, studentArrayList = it).apply {
                studentVerticalAdapterCallBack = this@StudentVerticalComponentList
            }
        }

        studentVerticalAdapter?.let {
            recyclerViewLayout(recyclerViewAdapter = it)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setAdapter(arrayList: ArrayList<*>) {
        messageErrorVisibility(arrayList = arrayList)

        studentArrayList?.clear()
        arrayList.forEach {
            (it as com.leandro1995.seito.model.entity.Student).let { student ->
                studentArrayList?.add(
                    Student(
                        name = student.name,
                        lastName = student.lastName,
                        email = student.email,
                        age = student.age,
                        sex = student.sex,
                        password = student.password,
                        teacher = student.teacher,
                        coins = student.coins
                    )
                )
            }
        }

        studentVerticalAdapter?.notifyDataSetChanged()
    }

    override fun student(student: Student) {
        studentVerticalComponentListCallBack?.student(student = com.leandro1995.seito.model.entity.Student(
            name = student.name,
            lastName = student.lastName,
            email = student.email,
            age = student.age,
            sex = student.sex,
            password = student.password,
            teacher = student.teacher,
            coins = student.coins
        ))
    }
}