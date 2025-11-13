package com.leandro1995.seito.activity

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.databinding.ActivityTeacherHomeBinding

class TeacherHomeActivity : ActivityAmbient<ActivityTeacherHomeBinding>() {

    override var idLayout: Int = R.layout.activity_teacher_home

    override var isBottomNavigationPadding: Boolean = false

    override fun initView() {
        dataBinding?.menuBottomNavigationView?.setupWithNavController(
            (supportFragmentManager.findFragmentById(
                R.id.home_teacher_fragment
            ) as NavHostFragment).navController
        )
    }
}