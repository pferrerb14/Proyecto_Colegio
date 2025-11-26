package com.leandro1995.seito.util.design

import android.os.SystemClock
import android.widget.Chronometer
import com.leandro1995.seito.config.Setting
import java.util.Locale

class QuestionAnswerUtilDesign {

    companion object {
        fun timer(chronometer: Chronometer): String {
            val elapsed = SystemClock.elapsedRealtime() - chronometer.base

            val hour = (elapsed / 3600000).toInt()
            val minute = ((elapsed % 3600000) / 60000).toInt()
            val second = ((elapsed % 60000) / 1000).toInt()

            return String.format(
                Locale.getDefault(), Setting.TIME_CHRONOMETER_FORMAT, hour, minute, second
            )
        }
    }
}