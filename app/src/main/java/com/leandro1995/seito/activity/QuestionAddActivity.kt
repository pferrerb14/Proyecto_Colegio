package com.leandro1995.seito.activity

import androidx.activity.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.databinding.ActivityQuestionAddBinding
import com.leandro1995.seito.viewmodel.QuestionAddViewModel

class QuestionAddActivity : ActivityAmbient<ActivityQuestionAddBinding>() {

    private val questionAddViewModel by viewModels<QuestionAddViewModel>()

    override var idLayout: Int = R.layout.activity_question_add

    override fun initView() {
        dataBinding?.questionAddViewModel = questionAddViewModel
    }
}