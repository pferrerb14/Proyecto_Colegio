package com.leandro1995.seito.activity

import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.databinding.ActivityVideoDetailBinding
import com.leandro1995.seito.model.design.Toolbar

class VideoDetailActivity : ActivityAmbient<ActivityVideoDetailBinding>() {

    override var idLayout: Int = R.layout.activity_video_detail

    override fun initView() {
        dataBinding?.apply {
            Toolbar(
                this@VideoDetailActivity,
                appBarBlueInclude.toolbar,
                R.string.educational_videos_title,
                true,
                icArrow = R.drawable.ic_arrow_white
            ).config { finish() }
        }
    }
}