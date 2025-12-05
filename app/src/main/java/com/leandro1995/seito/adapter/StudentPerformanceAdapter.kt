package com.leandro1995.seito.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.leandro1995.seito.fragment.ExamGraphicFragment
import com.leandro1995.seito.fragment.ExerciseGraphicFragment

class StudentPerformanceAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                ExerciseGraphicFragment()
            }

            1 -> {
                ExamGraphicFragment()
            }

            else -> {
                ExerciseGraphicFragment()
            }
        }
    }

    override fun getItemCount(): Int {
        return 2
    }
}