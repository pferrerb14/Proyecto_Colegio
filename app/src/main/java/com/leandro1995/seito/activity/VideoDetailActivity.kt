package com.leandro1995.seito.activity

import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.component.list.callback.VideoVerticalListCallBack
import com.leandro1995.seito.component.list.model.Course
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.ActivityVideoDetailBinding
import com.leandro1995.seito.extension.parcelable
import com.leandro1995.seito.extension.youtubeStartActivity
import com.leandro1995.seito.model.design.Toolbar

class VideoDetailActivity : ActivityAmbient<ActivityVideoDetailBinding>(),
    VideoVerticalListCallBack {

    override var idLayout: Int = R.layout.activity_video_detail

    override fun initView() {
        dataBinding?.apply {
            Toolbar(
                materialToolbar = appBarBlueInclude.toolbar,
                idTitle = R.string.educational_videos_title,
                isArrow = true,
                icArrow = R.drawable.ic_arrow_white
            ).config { finish() }

            videoVerticalList.let { verticalList ->
                verticalList.videoVerticalListCallBack = this@VideoDetailActivity

                Setting.COURSE_ARRAY_LIST_PUT_EXTRA.parcelable<ArrayList<Course>>(
                    activity = this@VideoDetailActivity
                )?.let { courseArrayList ->
                    verticalList.setAdapter(arrayList = courseArrayList)
                }
            }
        }
    }

    override fun videoUrl(videoUrl: String) {
        youtubeStartActivity(url = videoUrl)
    }
}