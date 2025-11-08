package com.leandro1995.seito.intent.event

sealed class HomeIntentEvent {
    data class HomeView(val idBottomNavigation: Int, val idGraphNavigation: Int) : HomeIntentEvent()
}