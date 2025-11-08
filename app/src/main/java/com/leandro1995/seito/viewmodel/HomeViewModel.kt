package com.leandro1995.seito.viewmodel

import com.leandro1995.seito.R
import com.leandro1995.seito.intent.event.HomeIntentEvent
import com.leandro1995.seito.viewmodel.ambient.ViewModelAmbient

class HomeViewModel : ViewModelAmbient<Any, HomeIntentEvent>() {

    var isUserType = false

    override fun event(action: Int) {
        when (action) {
            HOME_VIEW_NAVIGATION -> {
                homeViewNavigation()
            }
        }
    }

    private fun homeViewNavigation() {
        if (isUserType) {
            emit(
                event = HomeIntentEvent.HomeView(
                    idBottomNavigation = R.menu.menu_home_student,
                    idGraphNavigation = R.navigation.nav_home_student
                )
            )
        } else {
            emit(event = HomeIntentEvent.HomeView(idBottomNavigation = 0, idGraphNavigation = 0))
        }
    }

    companion object {
        const val HOME_VIEW_NAVIGATION = 0
    }
}