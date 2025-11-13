package com.leandro1995.seito.component.list.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.R
import com.leandro1995.seito.component.list.adapter.viewholder.StudentVerticalViewHolder
import com.leandro1995.seito.component.list.callback.adapter.StudentVerticalAdapterCallBack
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.component.list.model.Student
import com.leandro1995.seito.databinding.ItemStudentVerticalBinding

class StudentVerticalAdapter(
    private val context: Context, private val studentArrayList: ArrayList<Student>
) : RecyclerView.Adapter<StudentVerticalViewHolder>(), ListAmbientOnclick {

    var studentVerticalAdapterCallBack: StudentVerticalAdapterCallBack? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): StudentVerticalViewHolder {
        return StudentVerticalViewHolder(
            itemStudentVerticalBinding = ItemStudentVerticalBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), listAmbientOnclick = this
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: StudentVerticalViewHolder, position: Int
    ) {
        holder.itemStudentVerticalBinding.apply {
            nameStudentText.text =
                "${studentArrayList[position].name} ${studentArrayList[position].lastName}"
            emailStudentText.text =
                context.getString(R.string.email_item_text, studentArrayList[position].email)
            ageStudentText.text =
                context.getString(R.string.age_item_text, studentArrayList[position].age)
        }
    }

    override fun getItemCount(): Int {
        return studentArrayList.size
    }

    override fun onclick(position: Int) {
        studentVerticalAdapterCallBack?.student(student = studentArrayList[position])
    }
}