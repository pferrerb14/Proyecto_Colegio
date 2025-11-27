package com.leandro1995.seito.activity

import android.view.View
import androidx.activity.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.adapter.viewholder.CourseAdapter
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.databinding.ActivityExamAddBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.intent.callback.action.ExamAddIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.ExamAddIntentEventCallBack
import com.leandro1995.seito.intent.config.action.ExamAddIntentActionConfig
import com.leandro1995.seito.intent.config.event.ExamAddIntentEventConfig
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Course
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

    private val courseArrayList = arrayListOf<Course>()

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

    override fun alertMessage(alertMessage: AlertMessage) {
        AppUtilDialog.dialogMaterialDesign(
            context = this, alertMessage = alertMessage, positiveButton = { finish() })
    }

    private fun spinnerConfig() {
        courseAdapter = CourseAdapter(context = this, courseArrayList = courseArrayList)
        dataBinding?.courseSpinner?.adapter = courseAdapter
    }
}