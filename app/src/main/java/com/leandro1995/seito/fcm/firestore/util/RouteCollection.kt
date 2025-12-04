package com.leandro1995.seito.fcm.firestore.util

import com.leandro1995.seito.fcm.firestore.config.Setting
import com.leandro1995.seito.model.entity.Exam

object RouteCollection {

    fun themeRute(idCourse: String) = "${Setting.COURSE}/${idCourse}/${idCourse}_${THEME}"

    fun subThemeRute(idCourse: String, idTheme: String) =
        "${themeRute(idCourse = idCourse)}/${idTheme}/${idCourse}_${SUB_THEME}"

    fun levelRute(idCourse: String, idTheme: String, idSubTeme: String) = "${
        subThemeRute(
            idCourse = idCourse, idTheme = idTheme
        )
    }/${idSubTeme}/${idCourse}_${SUB_THEME}_${LEVEL}"

    fun examRute(idExam: String) = "${Setting.EXAM}/${idExam}/${Setting.QUESTION}"

    fun answerRute(email: String) = "${Setting.ANSWER}/${email}/${Setting.EXAM}"

    private const val THEME = "THEME"
    private const val SUB_THEME = "SUB_THEME"
    private const val LEVEL = "LEVEL"
}