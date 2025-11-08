package com.leandro1995.seito.fcm.firestore.util

import com.leandro1995.seito.fcm.firestore.config.Setting

object RouteCollection {

    fun themeRute(idCourse: String) = "${Setting.COURSE}/${idCourse}/${idCourse}_${THEME}"

    fun subThemeRute(idCourse: String, idTheme: String) =
        "${themeRute(idCourse = idCourse)}/${idTheme}/${idCourse}_${SUB_THEME}"

    fun levelRute(idCourse: String, idTheme: String, idSubTeme: String) = "${
        subThemeRute(
            idCourse = idCourse, idTheme = idTheme
        )
    }/${idSubTeme}/${idCourse}_${SUB_THEME}_${LEVEL}"

    private const val THEME = "THEME"
    private const val SUB_THEME = "SUB_THEME"
    private const val LEVEL = "LEVEL"
}