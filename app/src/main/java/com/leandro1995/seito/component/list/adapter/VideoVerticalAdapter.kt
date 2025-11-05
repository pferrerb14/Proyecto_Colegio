package com.leandro1995.seito.component.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.component.list.adapter.viewholder.VideoVerticalViewHolder
import com.leandro1995.seito.component.list.callback.adapter.VideoVerticalAdapterCallBack
import com.leandro1995.seito.component.list.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.component.list.model.Course
import com.leandro1995.seito.databinding.ItemVideoVerticalBinding

class VideoVerticalAdapter(private val courseArrayList: ArrayList<Course>) :
    RecyclerView.Adapter<VideoVerticalViewHolder>(), ListAmbientOnclick {

    var videoVerticalAdapterCallBack: VideoVerticalAdapterCallBack? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): VideoVerticalViewHolder {
        return VideoVerticalViewHolder(
            itemVideoVerticalBinding = ItemVideoVerticalBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            ), listAmbientOnclick = this
        )
    }

    override fun onBindViewHolder(
        holder: VideoVerticalViewHolder, position: Int
    ) {
        holder.itemVideoVerticalBinding.apply {
            courseImageSimpleDraweeView.setImageURI(courseArrayList[position].imageUrl)
            courseNameTextView.text = courseArrayList[position].name
        }
    }

    override fun getItemCount(): Int {
        return courseArrayList.size
    }

    override fun onclick(position: Int) {
        videoVerticalAdapterCallBack?.videoUrl(videoUrl = courseArrayList[position].videoUrl)
    }
}