package com.leandro1995.seito.activity

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.databinding.ActivityHomeBinding

class HomeActivity : ActivityAmbient<ActivityHomeBinding>() {

    override var idLayout: Int = R.layout.activity_home

    override fun initView() {
        dataBinding?.menuBottomNavigationView?.setupWithNavController(
            (supportFragmentManager.findFragmentById(
                R.id.home_student_fragment
            ) as NavHostFragment).navController
        )
    }
}