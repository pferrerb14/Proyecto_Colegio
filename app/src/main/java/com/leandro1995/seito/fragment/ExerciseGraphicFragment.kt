package com.leandro1995.seito.fragment

import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.adapter.CourseAdapter
import com.leandro1995.seito.adapter.SubThemeAdapter
import com.leandro1995.seito.adapter.ThemeAdapter
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.config.callback.adapter.listener.ItemSelectedListenerCallBack
import com.leandro1995.seito.config.listener.ItemSelectedListener
import com.leandro1995.seito.databinding.FragmentExerciseGraphicBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.extension.string
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.ExerciseGraphicIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.ExerciseGraphicIntentEventCallBack
import com.leandro1995.seito.intent.config.action.ExerciseGraphicIntentActionConfig
import com.leandro1995.seito.intent.config.event.ExerciseGraphicIntentEventConfig
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.Note
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.util.design.ExerciseGraphicUtilDesign
import com.leandro1995.seito.util.dialog.AppUtilDialog
import com.leandro1995.seito.viewmodel.ExerciseGraphicViewModel

class ExerciseGraphicFragment : FragmentAmbient<FragmentExerciseGraphicBinding>(),
    ExerciseGraphicIntentActionCallBack, ExerciseGraphicIntentEventCallBack {

    private val exerciseGraphicViewModel by viewModels<ExerciseGraphicViewModel>()

    private val exerciseGraphicIntentEventConfig =
        ExerciseGraphicIntentEventConfig(exerciseGraphicIntentEventCallBack = this)
    private val exerciseGraphicIntentActionConfig =
        ExerciseGraphicIntentActionConfig(exerciseGraphicIntentActionCallBack = this)

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
        dataBinding?.exerciseGraphicViewModel = exerciseGraphicViewModel
        arrayConfig()
        itemSelectedListener()
        spinnerConfig()
    }

    override fun initEventToAction() {
        lifecycleScope {
            exerciseGraphicViewModel.event.collect { exerciseGraphicIntentEvent ->
                exerciseGraphicIntentEventConfig.initConfig(event = exerciseGraphicIntentEvent)
            }
        }

        lifecycleScope {
            exerciseGraphicViewModel.action.collect { exerciseGraphicIntentAction ->
                exerciseGraphicIntentActionConfig.initConfig(event = exerciseGraphicIntentAction)
            }
        }
    }

    override fun putExtra() {
        exerciseGraphicViewModel.student.email =
            Setting.EMAIL_PUT_EXTRA.string(activity = requireActivity()).orEmpty()
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
                    exerciseGraphicViewModel.courseSelect(course = item)
                }
            }
        }

        themeItemSelectedListener = ItemSelectedListener(arrayList = themeArrayList).apply {
            itemSelectedListenerCallBack = object : ItemSelectedListenerCallBack<Theme> {
                override fun item(item: Theme) {
                    exerciseGraphicViewModel.themeSelect(theme = item)
                }
            }
        }

        subThemeItemSelectedListener = ItemSelectedListener(arrayList = suThemeArrayList).apply {
            itemSelectedListenerCallBack = object : ItemSelectedListenerCallBack<SubTheme> {
                override fun item(item: SubTheme) {
                    exerciseGraphicViewModel.subThemeSelect(subTheme = item)
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

    override fun loading(loading: Loading) {
        dataBinding?.loadingComponent?.startService(loading = loading) {
            exerciseGraphicViewModel.service(idService = loading.idService)
        }
    }

    override fun startService() {
        exerciseGraphicViewModel.button.invoke(ExerciseGraphicViewModel.COURSE)
    }

    override fun courseArrayList(courseArrayList: ArrayList<Course>) {
        this.courseArrayList.clear()
        this.courseArrayList.add(Course(name = getString(R.string.select_hint)))
        this.courseArrayList.addAll(courseArrayList)

        courseAdapter?.notifyDataSetChanged()
    }

    override fun themeArrayList(themeArrayList: ArrayList<Theme>) {
        this.themeArrayList.clear()
        this.themeArrayList.add(Theme(name = getString(R.string.select_hint)))
        this.themeArrayList.addAll(themeArrayList)

        dataBinding?.themeSpinner?.setSelection(0)
        themeAdapter?.notifyDataSetChanged()
    }

    override fun subThemeArrayList(subThemeArrayList: ArrayList<SubTheme>) {
        this.suThemeArrayList.clear()
        this.suThemeArrayList.add(SubTheme(name = getString(R.string.select_hint)))
        this.suThemeArrayList.addAll(subThemeArrayList)

        dataBinding?.subThemeSpinner?.setSelection(0)
        subThemeAdapter?.notifyDataSetChanged()
    }

    override fun noteLeveOneArrayList(noteArrayList: ArrayList<Note>) {
        dataBinding?.noteOnePieGraphicComponent?.graphic(
            pieEntryArrayList = ExerciseGraphicUtilDesign.totalPromise(
                context = requireContext(), noteArrayList = noteArrayList
            ), titleCenter = getString(
                R.string.average_value_text,
                ExerciseGraphicUtilDesign.promise(noteArrayList = noteArrayList)
            )
        )
    }

    override fun noteLeveTwoArrayList(noteArrayList: ArrayList<Note>) {

    }

    override fun alertMessage(alertMessage: AlertMessage) {
        AppUtilDialog.dialogMaterialDesign(context = requireContext(), alertMessage = alertMessage)
    }
}