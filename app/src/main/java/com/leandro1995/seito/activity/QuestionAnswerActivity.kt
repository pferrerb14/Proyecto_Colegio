package com.leandro1995.seito.activity

import android.widget.Chronometer
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.adapter.QuestionAnswerAdapter
import com.leandro1995.seito.databinding.ActivityQuestionAnswerBinding
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.util.design.QuestionAnswerUtilDesign

class QuestionAnswerActivity : ActivityAmbient<ActivityQuestionAnswerBinding>() {

    private var questionAnswerAdapter: QuestionAnswerAdapter? = null

    override var idLayout: Int = R.layout.activity_question_answer

    override fun initView() {
        dataBinding?.apply {
            Toolbar(
                materialToolbar = appBarBlueInclude.toolbar,
                idTitle = R.string.answer_questions_title,
                isArrow = true,
                icArrow = R.drawable.ic_arrow_white
            ).config { finish() }
        }

        chronometerConfig()
    }

    private fun chronometerConfig() {
        dataBinding?.timeChronometer?.apply {
            onChronometerTickListener = Chronometer.OnChronometerTickListener { chronometer ->
                chronometer.text = QuestionAnswerUtilDesign.timer(
                    chronometer = chronometer, timeElapsed = getString(R.string.time_elapsed_text)
                )
            }
            start()
        }
    }
}