package com.leandro1995.seito.intent.config.action

import com.leandro1995.seito.intent.action.ExerciseGraphicIntentAction
import com.leandro1995.seito.intent.callback.action.ExerciseGraphicIntentActionCallBack
import com.leandro1995.seito.intent.config.ambient.IntentConfigAmbient

class ExerciseGraphicIntentActionConfig(private val exerciseGraphicIntentActionCallBack: ExerciseGraphicIntentActionCallBack?) :
    IntentConfigAmbient<ExerciseGraphicIntentAction>() {

    override fun initConfig(event: ExerciseGraphicIntentAction?) {
        if (event != null) {
            event.courseArrayList?.let {
                exerciseGraphicIntentActionCallBack?.courseArrayList(courseArrayList = it)
            }

            event.themeArrayList?.let {
                exerciseGraphicIntentActionCallBack?.themeArrayList(themeArrayList = it)
            }

            event.subThemeArrayList?.let {
                exerciseGraphicIntentActionCallBack?.subThemeArrayList(subThemeArrayList = it)
            }

            event.noteLeveOneArrayList?.let {
                exerciseGraphicIntentActionCallBack?.noteLeveOneArrayList(noteArrayList = it)
            }

            event.noteLeveTwoArrayList?.let {
                exerciseGraphicIntentActionCallBack?.noteLeveTwoArrayList(noteArrayList = it)
            }
        } else {
            exerciseGraphicIntentActionCallBack?.startService()
        }
    }
}