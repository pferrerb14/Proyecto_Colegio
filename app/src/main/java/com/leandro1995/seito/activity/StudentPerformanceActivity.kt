package com.leandro1995.seito.activity

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.adapter.StudentPerformanceAdapter
import com.leandro1995.seito.databinding.ActivityStudentPerformanceBinding
import com.leandro1995.seito.model.design.Toolbar

class StudentPerformanceActivity : ActivityAmbient<ActivityStudentPerformanceBinding>() {

    override var idLayout: Int = R.layout.activity_student_performance

    override fun initView() {
        dataBinding?.apply {
            Toolbar(
                materialToolbar = appBarBlueInclude.toolbar,
                idTitle = R.string.student_statistics_title,
                isArrow = true,
                icArrow = R.drawable.ic_arrow_white
            ).config { finish() }

            performanceTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    performanceViewPager.currentItem = tab?.position ?: 0
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })

            performanceViewPager.registerOnPageChangeCallback(object :
                ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)
                    performanceTabLayout.getTabAt(position)?.select()
                }
            })

            performanceViewPager.adapter =
                StudentPerformanceAdapter(fragmentActivity = this@StudentPerformanceActivity)
        }
    }
}