package com.leandro1995.seito.activity

import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.databinding.ActivityQuestionAnswerBinding
import com.leandro1995.seito.model.design.Toolbar

class QuestionAnswerActivity : ActivityAmbient<ActivityQuestionAnswerBinding>() {

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
    }
}