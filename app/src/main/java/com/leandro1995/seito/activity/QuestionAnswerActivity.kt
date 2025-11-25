package com.leandro1995.seito.activity

import android.annotation.SuppressLint
import android.widget.Chronometer
import androidx.activity.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.adapter.QuestionOptionAdapter
import com.leandro1995.seito.background.coroutine.BackGroundCoroutine
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.config.callback.adapter.QuestionOptionAdapterCallBack
import com.leandro1995.seito.databinding.ActivityQuestionAnswerBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.extension.parcelable
import com.leandro1995.seito.intent.callback.action.QuestionAnswerIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.QuestionAnswerIntentEventCallBack
import com.leandro1995.seito.intent.config.action.QuestionAnswerIntentActionConfig
import com.leandro1995.seito.intent.config.event.QuestionAnswerIntentEventConfig
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.design.Toolbar
import com.leandro1995.seito.model.entity.Option
import com.leandro1995.seito.model.entity.Question
import com.leandro1995.seito.protodatastore.config.UserProtoDataStoreConfig
import com.leandro1995.seito.util.design.QuestionAnswerUtilDesign
import com.leandro1995.seito.util.dialog.AppUtilDialog
import com.leandro1995.seito.viewmodel.QuestionAnswerViewModel

class QuestionAnswerActivity : ActivityAmbient<ActivityQuestionAnswerBinding>(),
    QuestionAnswerIntentActionCallBack, QuestionAnswerIntentEventCallBack,
    QuestionOptionAdapterCallBack {

    private var questionArrayList = arrayListOf<Question>()
    private var questionAnswerAdapter: QuestionOptionAdapter? = null
    private val questionAnswerViewModel by viewModels<QuestionAnswerViewModel>()
    private var questionAnswerIntentEventConfig =
        QuestionAnswerIntentEventConfig(questionAnswerIntentEventCallBack = this)
    private var questionAnswerIntentActionConfig =
        QuestionAnswerIntentActionConfig(questionAnswerIntentActionCallBack = this)

    private val backGroundCoroutine = BackGroundCoroutine()

    override var idLayout: Int = R.layout.activity_question_answer

    override fun initView() {
        dataBinding?.apply {
            questionAnswerViewModel = this@QuestionAnswerActivity.questionAnswerViewModel
            Toolbar(
                materialToolbar = appBarBlueInclude.toolbar,
                idTitle = R.string.answer_questions_title,
                isArrow = true,
                icArrow = R.drawable.ic_arrow_white
            ).config { finish() }
        }

        chronometerConfig()
        questionViewPageConfig()
    }

    override fun putExtra() {
        Setting.QUESTION_ARRAY_LIST_PUT_EXTRA.parcelable<ArrayList<Question>>(activity = this)
            ?.let {
                questionAnswerViewModel.questionArrayList = it
            }
    }

    override fun initEventToAction() {
        lifecycleScope {
            questionAnswerViewModel.event.collect { questionAnswerIntentEvent ->
                questionAnswerIntentEventConfig.initConfig(event = questionAnswerIntentEvent)
            }
        }

        lifecycleScope {
            questionAnswerViewModel.action.collect { questionAnswerIntentAction ->
                questionAnswerIntentActionConfig.initConfig(event = questionAnswerIntentAction)
            }
        }
    }

    private fun chronometerConfig() {
        dataBinding?.timeChronometer?.apply {
            onChronometerTickListener = Chronometer.OnChronometerTickListener { chronometer ->
                chronometer.text = QuestionAnswerUtilDesign.timer(
                    chronometer = chronometer, timeElapsed = getString(R.string.time_elapsed_text)
                )
            }
            start()
        }
    }

    private fun questionViewPageConfig() {
        questionAnswerAdapter = QuestionOptionAdapter(
            fragmentActivity = this, questionArrayList = questionArrayList
        ).apply { questionOptionAdapterCallBack = this@QuestionAnswerActivity }
        dataBinding?.questionViewPager?.apply {
            isUserInputEnabled = false
            adapter = questionAnswerAdapter
        }
    }

    override fun loading(loading: Loading) {
        dataBinding?.loadingComponent?.startService(loading = loading) {
            questionAnswerViewModel.service(idService = loading.idService)
        }
    }

    override fun startView() {
        backGroundCoroutine.start {
            questionAnswerViewModel.apply {
                coin = UserProtoDataStoreConfig.getCoins()
                student.email = UserProtoDataStoreConfig.getEmail()
                button.invoke(QuestionAnswerViewModel.START_VIEW)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun questionArrayList(questionArrayList: ArrayList<Question>) {
        this.questionArrayList.clear()
        this.questionArrayList.addAll(questionArrayList)

        questionAnswerAdapter?.notifyDataSetChanged()
    }

    override fun page(position: Int) {
        dataBinding?.questionViewPager?.setCurrentItem(position, false)
    }

    override fun coin(coin: Int) {
        dataBinding?.moneyText?.text = getString(R.string.available_currency_text, coin)
    }

    override fun isEnableNextButton(isEnable: Boolean) {
        dataBinding?.nextButton?.isEnabled = isEnable
    }

    override fun alertMessage(alertMessage: AlertMessage) {
        AppUtilDialog.dialogMaterialDesign(context = this, alertMessage = alertMessage)
    }

    override fun completeQuestionMessage(alertMessage: AlertMessage) {
        AppUtilDialog.dialogMaterialDesign(context = this, alertMessage = alertMessage) { finish() }
    }

    override fun option(option: Option) {
        questionAnswerViewModel.option(option = option)
    }
}