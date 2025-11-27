package com.leandro1995.seito.activity

import android.view.View
import androidx.activity.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.adapter.CourseAdapter
import com.leandro1995.seito.adapter.SubThemeAdapter
import com.leandro1995.seito.adapter.ThemeAdapter
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.callback.adapter.listener.ItemSelectedListenerCallBack
import com.leandro1995.seito.config.listener.ItemSelectedListener
import com.leandro1995.seito.databinding.ActivityExamAddBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.intent.callback.action.ExamAddIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.ExamAddIntentEventCallBack
import com.leandro1995.seito.intent.config.action.ExamAddIntentActionConfig
import com.leandro1995.seito.intent.config.event.ExamAddIntentEventConfig
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Course
import com.leandro1995.seito.model.entity.SubTheme
import com.leandro1995.seito.model.entity.Theme
import com.leandro1995.seito.util.dialog.AppUtilDialog
import com.leandro1995.seito.viewmodel.ExamAddViewModel

class ExamAddActivity : ActivityAmbient<ActivityExamAddBinding>(), ExamAddIntentActionCallBack,
    ExamAddIntentEventCallBack {

    private val examAddViewModel by viewModels<ExamAddViewModel>()
    private val examAddIntentEventConfig =
        ExamAddIntentEventConfig(examAddIntentEventCallBack = this)
    private val examAddIntentActionConfig =
        ExamAddIntentActionConfig(examAddIntentActionCallBack = this)

    private var courseAdapter: CourseAdapter? = null
    private var themeAdapter: ThemeAdapter? = null
    private var subThemeAdapter: SubThemeAdapter? = null
    private var courseItemSelectedListener: ItemSelectedListener<Course>? = null
    private var themeItemSelectedListener: ItemSelectedListener<Theme>? = null
    private var subThemeItemSelectedListener: ItemSelectedListener<SubTheme>? = null

    private val courseArrayList = arrayListOf<Course>()
    private val themeArrayList = arrayListOf<Theme>()
    private val suThemeArrayList = arrayListOf<SubTheme>()

    override var idLayout: Int = R.layout.activity_exam_add

    override fun initView() {
        dataBinding?.apply {
            this.examAddViewModel = examAddViewModel
            Toolbar(
                materialToolbar = appBarBlueInclude.toolbar,
                idTitle = R.string.create_exam_title,
                isArrow = true,
                icArrow = R.drawable.ic_arrow_white
            ).config { finish() }
        }

        itemSelectedListener()
        spinnerConfig()
    }

    override fun initEventToAction() {
        lifecycleScope {
            examAddViewModel.event.collect { examAddIntentEvent ->
                examAddIntentEventConfig.initConfig(event = examAddIntentEvent)
            }
        }

        lifecycleScope {
            examAddViewModel.action.collect { examAddIntentAction ->
                examAddIntentActionConfig.initConfig(event = examAddIntentAction)
            }
        }
    }

    override fun loading(loading: Loading) {
        dataBinding?.loadingComponent?.startService(loading = loading) {
            examAddViewModel.service(idService = loading.idService)
        }
    }

    override fun startService() {
        examAddViewModel.button.invoke(ExamAddViewModel.COURSE)
    }

    override fun courseArrayList(courseArrayList: ArrayList<Course>) {
        this.courseArrayList.clear()
        this.courseArrayList.addAll(courseArrayList)

        dataBinding?.registerScroll?.visibility = View.VISIBLE
        courseAdapter?.notifyDataSetChanged()
    }

    override fun themeArrayList(themeArrayList: ArrayList<Theme>) {
        this.themeArrayList.clear()
        this.themeArrayList.addAll(themeArrayList)

        dataBinding?.registerScroll?.visibility = View.VISIBLE
        themeAdapter?.notifyDataSetChanged()
    }

    override fun subThemeArrayList(subThemeArrayList: ArrayList<SubTheme>) {
        this.suThemeArrayList.clear()
        this.suThemeArrayList.addAll(subThemeArrayList)

        subThemeAdapter?.notifyDataSetChanged()
    }

    override fun alertMessage(alertMessage: AlertMessage) {
        AppUtilDialog.dialogMaterialDesign(
            context = this, alertMessage = alertMessage, positiveButton = { finish() })
    }

    private fun itemSelectedListener() {
        courseItemSelectedListener = ItemSelectedListener(arrayList = courseArrayList).apply {
            itemSelectedListenerCallBack = object : ItemSelectedListenerCallBack<Course> {
                override fun item(item: Course) {
                    examAddViewModel.course = item
                    examAddViewModel.button.invoke(ExamAddViewModel.THEME)
                }
            }
        }

        themeItemSelectedListener = ItemSelectedListener(arrayList = themeArrayList).apply {
            itemSelectedListenerCallBack = object : ItemSelectedListenerCallBack<Theme> {
                override fun item(item: Theme) {
                    examAddViewModel.theme = item
                    examAddViewModel.button.invoke(ExamAddViewModel.SUB_THEME)
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
        courseAdapter = CourseAdapter(context = this, courseArrayList = courseArrayList)
        dataBinding?.courseSpinner?.apply {
            adapter = courseAdapter
            onItemSelectedListener = courseItemSelectedListener
        }

        themeAdapter = ThemeAdapter(context = this, themeArrayList = themeArrayList)
        dataBinding?.themeSpinner?.apply {
            adapter = themeAdapter
            onItemSelectedListener = themeItemSelectedListener
        }

        subThemeAdapter = SubThemeAdapter(context = this, subThemeArrayList = suThemeArrayList)
        dataBinding?.subThemeSpinner?.apply {
            adapter = subThemeAdapter
            onItemSelectedListener = subThemeItemSelectedListener
        }
    }
}