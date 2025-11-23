package com.leandro1995.seito.fragment

import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.leandro1995.seito.R
import com.leandro1995.seito.adapter.OptionAdapter
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentQuestionOptionBinding
import com.leandro1995.seito.extension.argumentParcelable
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.model.entity.Option
import com.leandro1995.seito.model.entity.Question

class QuestionOptionFragment : FragmentAmbient<FragmentQuestionOptionBinding>() {

    private var question: Question? = null

    override var idLayout: Int = R.layout.fragment_question_option

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Setting.QUESTION_BUNDLE.argumentParcelable<Question>(bundle = arguments)?.let {
            question = it
        }
    }

    override fun initView() {
        dataBinding?.apply {
            questionImageSimpleDraweeView.setImageURI(question?.imageUrl.orEmpty())
            questionNameText.text = question?.name.orEmpty()
            questionOptionRecyclerView.let { optionRecyclerView ->
                optionRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                optionRecyclerView.adapter =
                    OptionAdapter(optionArrayList = question?.optionArrayList ?: arrayListOf())
            }
        }
    }

    companion object {
        fun newInstance(question: Question) = QuestionOptionFragment().apply {
            arguments = Bundle().apply {
                putParcelable(Setting.QUESTION_BUNDLE, question)
            }
        }
    }
}