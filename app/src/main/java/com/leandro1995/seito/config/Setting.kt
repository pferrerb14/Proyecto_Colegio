package com.leandro1995.seito.config

object Setting {

    const val EMAIL_REGEX =
        "^[A-Za-z0-9._%+-]{1,64}@[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?)+$"

    const val NAME_FILE_DATA_STORE = "seito.pb"

    const val CODE_LENGTH = 4
    const val PASSWORD_LENGTH = 6

    const val TEACHER_BUNDLE = "teacher_bundle"
    const val THEME_BUNDLE = "theme_put_bundle"
    const val ID_COURSE_BUNDLE = "id_course_bundle"
    const val ID_THEME_BUNDLE = "id_theme_bundle"
    const val ID_SUB_THEME_BUNDLE = "sub_theme_bundle"

    const val COURSE_ARRAY_LIST_PUT_EXTRA = "course_array_list_put_extra"
    const val COURSE_PUT_EXTRA = "course_put_extra"
}