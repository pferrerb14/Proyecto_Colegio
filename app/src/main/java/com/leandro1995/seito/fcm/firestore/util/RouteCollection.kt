package com.leandro1995.seito.fcm.firestore.util

import com.leandro1995.seito.fcm.firestore.config.Setting

object RouteCollection {

    fun themeRute(idCourse: String) = "${Setting.COURSE}/${idCourse}/${idCourse}_${THEME}"

    private const val THEME = "THEME"
}