package com.leandro1995.seito.config

import android.content.Context
import com.leandro1995.seito.R

object Setting {

    const val EMAIL_REGEX =
        "^[A-Za-z0-9._%+-]{1,64}@[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?(?:\\.[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])?)+$"

    const val NAME_FILE_DATA_STORE = "seito.pb"

    const val TIME_CHRONOMETER_FORMAT = "%02d:%02d:%02d"

    const val CODE_LENGTH = 4
    const val PASSWORD_LENGTH = 6
    const val OPTION_LENGTH = 5
    const val QUESTION_LENGTH = 5
    const val QUESTION_SELECT_MAX = 5

    const val DISCOUNT_CURRENCY = 100
    const val NOTE_MAXIMUM = 20

    const val TEACHER_BUNDLE = "teacher_bundle"
    const val THEME_BUNDLE = "theme_put_bundle"
    const val ID_COURSE_BUNDLE = "id_course_bundle"
    const val ID_THEME_BUNDLE = "id_theme_bundle"
    const val ID_SUB_THEME_BUNDLE = "sub_theme_bundle"
    const val QUESTION_BUNDLE = "question_bundle"

    const val COURSE_ARRAY_LIST_PUT_EXTRA = "course_array_list_put_extra"
    const val COURSE_PUT_EXTRA = "course_put_extra"
    const val LEVEL_PUT_EXTRA = "level_put_extra"
    const val QUESTION_ARRAY_LIST_PUT_EXTRA = "question_array_list_put_extra"
    const val ANSWER_ARRAY_LIST_PUT_EXTRA = "answer_array_list_put_extra"
    const val TYPE_ANSWER_QUESTION_PUT_EXTRA = "type_answer_question_put_extra"
    const val GROUP_ID_PUT_EXTRA = "group_id_put_extra"
    const val EMAIL_PUT_EXTRA = "email_put_extra"
    const val NOTE_PUT_EXTRA = "note_put_extra"

    fun levelStringArrayList(context: Context) = arrayListOf(
        context.getString(R.string.previous_level_text), context.getString(R.string.level_text)
    )

    const val DATE_FORMAT = "dd/MM/yyyy"
}