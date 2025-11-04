package com.leandro1995.seito.component.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.adapter.viewholder.VideoGridViewHolder
import com.leandro1995.seito.component.list.model.Course
import com.leandro1995.seito.databinding.ItemVideoGridBinding

class VideoGridAdapter(private val courseArrayList: ArrayList<Course>) :
    RecyclerView.Adapter<VideoGridViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): VideoGridViewHolder {
        return VideoGridViewHolder(
            itemVideoGridBinding = ItemVideoGridBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(
        holder: VideoGridViewHolder, position: Int
    ) {

    }

    override fun getItemCount(): Int {
        return courseArrayList.size
    }
}