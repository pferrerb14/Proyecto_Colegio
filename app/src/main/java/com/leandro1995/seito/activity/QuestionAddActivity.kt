package com.leandro1995.seito.activity

import androidx.activity.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.databinding.ActivityQuestionAddBinding
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.viewmodel.QuestionAddViewModel

class QuestionAddActivity : ActivityAmbient<ActivityQuestionAddBinding>() {

    private val questionAddViewModel by viewModels<QuestionAddViewModel>()

    override var idLayout: Int = R.layout.activity_question_add

    override fun initView() {
        dataBinding?.apply {
            questionAddViewModel = this@QuestionAddActivity.questionAddViewModel
            Toolbar(
                materialToolbar = appBarBlueInclude.toolbar,
                idTitle = R.string.add_question_title,
                icArrow = R.drawable.ic_arrow_white,
                isArrow = true
            ).config { finish() }
        }
    }
}