package com.leandro1995.seito.config

object Setting {

    const val EMAIL_REGEX =
        "^[A-Za-z0-9._%+-]{1,64}@[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?)+$"

    const val NAME_FILE_DATA_STORE = "seito.pb"

    const val CODE_LENGTH = 4

    const val TEACHER_BUNDLE = "teacher_bundle"
}