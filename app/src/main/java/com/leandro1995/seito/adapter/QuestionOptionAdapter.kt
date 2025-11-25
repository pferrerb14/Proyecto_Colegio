package com.leandro1995.seito.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.leandro1995.seito.config.callback.adapter.QuestionOptionAdapterCallBack
import com.leandro1995.seito.fragment.QuestionOptionFragment
import com.leandro1995.seito.model.entity.Question

class QuestionOptionAdapter(
    fragmentActivity: FragmentActivity, private val questionArrayList: ArrayList<Question>
) : FragmentStateAdapter(fragmentActivity) {

    var questionOptionAdapterCallBack: QuestionOptionAdapterCallBack? = null

    override fun createFragment(position: Int): Fragment {
        return QuestionOptionFragment.newInstance(
            question = questionArrayList[position],
            questionOptionAdapterCallBack = questionOptionAdapterCallBack
        )
    }

    override fun getItemCount(): Int {
        return questionArrayList.size
    }
}