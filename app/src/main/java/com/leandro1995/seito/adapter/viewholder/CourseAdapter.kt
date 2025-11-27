package com.leandro1995.seito.adapter.viewholder

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.leandro1995.seito.R
import com.leandro1995.seito.model.entity.Course

class CourseAdapter(private val context: Context, private val courseArrayList: ArrayList<Course>) :
    BaseAdapter() {

    private val inflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return courseArrayList.size
    }

    override fun getItem(position: Int): Any? {
        return courseArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(
        position: Int, convertView: View?, parent: ViewGroup?
    ): View? {
        return inflater.inflate(R.layout.item_spinner, null, false).apply {
            findViewById<TextView>(R.id.title_text).text = courseArrayList[position].name
        }
    }
}