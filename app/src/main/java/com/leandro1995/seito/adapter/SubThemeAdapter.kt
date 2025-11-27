package com.leandro1995.seito.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.leandro1995.seito.R
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.model.entity.Theme

class SubThemeAdapter(
    private val context: Context, private val subThemeArrayList: ArrayList<SubTheme>
) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return subThemeArrayList.size
    }

    override fun getItem(position: Int): Any? {
        return subThemeArrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(
        position: Int, convertView: View?, parent: ViewGroup?
    ): View? {
        return inflater.inflate(R.layout.item_spinner, null, false).apply {
            findViewById<TextView>(R.id.title_text).text = subThemeArrayList[position].name
        }
    }
}