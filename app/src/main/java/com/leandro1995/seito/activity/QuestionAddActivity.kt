package com.leandro1995.seito.activity

import android.app.Activity
import android.content.Intent
import androidx.activity.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.component.list.callback.OptionVerticalComponentListCallBack
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.ActivityQuestionAddBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.extension.parcelable
import com.leandro1995.seito.extension.visible
import com.leandro1995.seito.intent.callback.action.QuestionAddIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.QuestionAddIntentEventCallBack
import com.leandro1995.seito.intent.config.action.QuestionAddIntentActionConfig
import com.leandro1995.seito.intent.config.event.QuestionAddIntentEventConfig
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Level
import com.leandro1995.seito.model.entity.Option
import com.leandro1995.seito.util.dialog.AppUtilDialog
import com.leandro1995.seito.viewmodel.QuestionAddViewModel

class QuestionAddActivity : ActivityAmbient<ActivityQuestionAddBinding>(),
    QuestionAddIntentActionCallBack, QuestionAddIntentEventCallBack,
    OptionVerticalComponentListCallBack {

    private val questionAddViewModel by viewModels<QuestionAddViewModel>()
    private val questionAddIntentEventConfig =
        QuestionAddIntentEventConfig(questionAddIntentEventCallBack = this)
    private val questionAddIntentActionConfig =
        QuestionAddIntentActionConfig(questionAddIntentActionCallBack = this)

    override var idLayout: Int = R.layout.activity_question_add

    override fun initView() {
        dataBinding?.apply {
            questionAddViewModel = this@QuestionAddActivity.questionAddViewModel
            Toolbar(
                materialToolbar = appBarBlueInclude.toolbar,
                idTitle = R.string.add_question_title,
                icArrow = R.drawable.ic_arrow_white,
                isArrow = true
            ).config { finish() }
            optionVerticalComponentList.optionVerticalComponentListCallBack =
                this@QuestionAddActivity
        }
    }

    override fun initEventToAction() {
        lifecycleScope {
            questionAddViewModel.event.collect { questionAddIntentEvent ->
                questionAddIntentEventConfig.initConfig(event = questionAddIntentEvent)
            }
        }

        lifecycleScope {
            questionAddViewModel.action.collect { questionAddIntentAction ->
                questionAddIntentActionConfig.initConfig(event = questionAddIntentAction)
            }
        }
    }

    override fun putExtra() {
        Setting.LEVEL_PUT_EXTRA.parcelable<Level>(activity = this)?.let {
            questionAddViewModel.question.isType = it.isType
        }
    }

    override fun alertMessage(alertMessage: AlertMessage) {
        AppUtilDialog.dialogMaterialDesign(context = this, alertMessage = alertMessage)
    }

    override fun optionAddBottomSheet(isAnswer: Boolean) {
        AppUtilDialog.optionAddBottomSheet(
            fragmentManager = supportFragmentManager, isVisibleAnswer = isAnswer
        ) { option ->
            questionAddViewModel.option = option
            questionAddViewModel.button.invoke(QuestionAddViewModel.OPTION_ADD_VALIDATION_BOTTOM_SHEET)
        }
    }

    override fun registerAlertMessage(alertMessage: AlertMessage) {
        AppUtilDialog.dialogMaterialDesign(
            context = this, alertMessage = alertMessage, positiveButton = {
                setResult(RESULT_OK, Intent())
                finish()
            })
    }

    override fun loading(loading: Loading) {
        dataBinding?.loadingComponent?.startService(loading = loading) {
            questionAddViewModel.service(idService = loading.idService)
        }
    }

    override fun optionArrayList(optionArrayList: ArrayList<Option>) {
        dataBinding?.optionVerticalComponentList?.setAdapter(arrayList = optionArrayList)
    }

    override fun isOptionAddLink(isVisible: Boolean) {
        dataBinding?.addOptionTextView?.visibility = visible(isVisible = isVisible)
    }

    override fun deleteOption(position: Int) {
        questionAddViewModel.optionRemover(position = position)
    }
}