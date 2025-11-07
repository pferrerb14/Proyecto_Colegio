package com.leandro1995.seito.fcm.firestore.util

import com.leandro1995.seito.fcm.firestore.config.Setting

object RouteCollection {

    fun themeRute(idCourse: String) = "${Setting.COURSE}/${idCourse}/${idCourse}_${THEME}"

    fun subThemeRute(idCourse: String, id: String) =
        "${themeRute(idCourse = idCourse)}/${id}/${idCourse}_${SUB_THEME}"

    private const val THEME = "THEME"
    private const val SUB_THEME = "SUB_THEME"
}