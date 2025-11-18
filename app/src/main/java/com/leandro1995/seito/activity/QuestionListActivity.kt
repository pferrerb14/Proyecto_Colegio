package com.leandro1995.seito.activity

import androidx.activity.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.databinding.ActivityQuestionListBinding
import com.leandro1995.seito.viewmodel.QuestionListViewModel

class QuestionListActivity : ActivityAmbient<ActivityQuestionListBinding>() {

    val questionListViewModel by viewModels<QuestionListViewModel>()

    override var idLayout: Int = R.layout.activity_question_list
}