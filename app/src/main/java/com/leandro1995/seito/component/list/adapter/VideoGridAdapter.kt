package com.leandro1995.seito.component.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.adapter.viewholder.VideoGridViewHolder
import com.leandro1995.seito.component.list.callback.adapter.VideoGridAdapterCallBack
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.component.list.model.Course
import com.leandro1995.seito.databinding.ItemVideoGridBinding

class VideoGridAdapter(private val courseArrayList: ArrayList<Course>) :
    RecyclerView.Adapter<VideoGridViewHolder>(), ListAmbientOnclick {

    var videoGridAdapterCallBack: VideoGridAdapterCallBack? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): VideoGridViewHolder {
        return VideoGridViewHolder(
            itemVideoGridBinding = ItemVideoGridBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), this
        )
    }

    override fun onBindViewHolder(
        holder: VideoGridViewHolder, position: Int
    ) {
        holder.itemVideoGridBinding.apply {
            courseImageSimpleDraweeView.setImageURI(courseArrayList[position].imageUrl)
            courseNameTextView.text = courseArrayList[position].name
        }
    }

    override fun getItemCount(): Int {
        return courseArrayList.size
    }

    override fun onclick(position: Int) {
        videoGridAdapterCallBack?.videoUrl(videoUrl = courseArrayList[position].videoUrl)
    }
}