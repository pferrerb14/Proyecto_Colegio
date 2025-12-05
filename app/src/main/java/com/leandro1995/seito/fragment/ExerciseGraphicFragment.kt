package com.leandro1995.seito.fragment

import com.leandro1995.seito.R
import com.leandro1995.seito.adapter.CourseAdapter
import com.leandro1995.seito.adapter.SubThemeAdapter
import com.leandro1995.seito.adapter.ThemeAdapter
import com.leandro1995.seito.config.callback.adapter.listener.ItemSelectedListenerCallBack
import com.leandro1995.seito.config.listener.ItemSelectedListener
import com.leandro1995.seito.databinding.FragmentExerciseGraphicBinding
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.model.entity.Theme

class ExerciseGraphicFragment : FragmentAmbient<FragmentExerciseGraphicBinding>() {

    private var courseAdapter: CourseAdapter? = null
    private var themeAdapter: ThemeAdapter? = null
    private var subThemeAdapter: SubThemeAdapter? = null

    private var courseItemSelectedListener: ItemSelectedListener<Course>? = null
    private var themeItemSelectedListener: ItemSelectedListener<Theme>? = null
    private var subThemeItemSelectedListener: ItemSelectedListener<SubTheme>? = null

    private val courseArrayList = arrayListOf<Course>()
    private val themeArrayList = arrayListOf<Theme>()
    private val suThemeArrayList = arrayListOf<SubTheme>()

    override var idLayout: Int = R.layout.fragment_exercise_graphic

    override fun initView() {
        arrayConfig()
        itemSelectedListener()
        spinnerConfig()
    }

    private fun arrayConfig() {
        courseArrayList.add(Course(name = getString(R.string.select_hint)))
        themeArrayList.add(Theme(name = getString(R.string.select_hint)))
        suThemeArrayList.add(SubTheme(name = getString(R.string.select_hint)))
    }

    private fun itemSelectedListener() {
        courseItemSelectedListener = ItemSelectedListener(arrayList = courseArrayList).apply {
            itemSelectedListenerCallBack = object : ItemSelectedListenerCallBack<Course> {
                override fun item(item: Course) {

                }
            }
        }

        themeItemSelectedListener = ItemSelectedListener(arrayList = themeArrayList).apply {
            itemSelectedListenerCallBack = object : ItemSelectedListenerCallBack<Theme> {
                override fun item(item: Theme) {

                }
            }
        }

        subThemeItemSelectedListener = ItemSelectedListener(arrayList = suThemeArrayList).apply {
            itemSelectedListenerCallBack = object : ItemSelectedListenerCallBack<SubTheme> {
                override fun item(item: SubTheme) {

                }
            }
        }
    }

    private fun spinnerConfig() {
        courseAdapter = CourseAdapter(context = requireContext(), courseArrayList = courseArrayList)
        dataBinding?.courseSpinner?.apply {
            adapter = courseAdapter
            onItemSelectedListener = courseItemSelectedListener
        }

        themeAdapter = ThemeAdapter(context = requireContext(), themeArrayList = themeArrayList)
        dataBinding?.themeSpinner?.apply {
            adapter = themeAdapter
            onItemSelectedListener = themeItemSelectedListener
        }

        subThemeAdapter =
            SubThemeAdapter(context = requireContext(), subThemeArrayList = suThemeArrayList)
        dataBinding?.subThemeSpinner?.apply {
            adapter = subThemeAdapter
            onItemSelectedListener = subThemeItemSelectedListener
        }
    }
}