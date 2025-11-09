package com.leandro1995.seito.activity

import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.background.coroutine.BackGroundCoroutine
import com.leandro1995.seito.databinding.ActivityHomeBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.intent.callback.event.HomeIntentEventCallBack
import com.leandro1995.seito.intent.config.event.HomeIntentEventConfig
import com.leandro1995.seito.protodatastore.config.UserProtoDataStoreConfig
import com.leandro1995.seito.viewmodel.HomeViewModel

class HomeActivity : ActivityAmbient<ActivityHomeBinding>(), HomeIntentEventCallBack {

    private val homeViewModel by viewModels<HomeViewModel>()
    private val homeIntentEventConfig = HomeIntentEventConfig(homeIntentEventCallBack = this)
    private val backGroundCoroutine = BackGroundCoroutine()

    override var idLayout: Int = R.layout.activity_home

    override fun initView() {
        backGroundCoroutine.start {
            homeViewModel.isUserType = UserProtoDataStoreConfig.getIsUserType()
            homeViewModel.button.invoke(HomeViewModel.HOME_VIEW_NAVIGATION)
        }
    }

    override fun initEventToAction() {
        lifecycleScope {
            homeViewModel.event.collect { homeIntentEvent ->
                homeIntentEventConfig.initConfig(event = homeIntentEvent)
            }
        }
    }

    override fun homeView(idBottomNavigation: Int, idGraphNavigation: Int) {
        val naveCationFragment =
            (supportFragmentManager.findFragmentById(R.id.home_student_fragment) as NavHostFragment)
        naveCationFragment.navController.setGraph(idGraphNavigation)

        dataBinding?.menuBottomNavigationView?.apply { inflateMenu(idBottomNavigation) }
            ?.setupWithNavController(naveCationFragment.navController)
    }
}