package com.leandro1995.seito.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.leandro1995.seito.fragment.QuestionOptionFragment
import com.leandro1995.seito.model.entity.Question

class QuestionAnswerAdapter(
    fragmentActivity: FragmentActivity, private val questionArrayList: ArrayList<Question>
) : FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return QuestionOptionFragment()
    }

    override fun getItemCount(): Int {
        return questionArrayList.size
    }
}