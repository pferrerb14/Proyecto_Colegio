package com.leandro1995.seito.fragment

import android.content.Intent
import androidx.fragment.app.viewModels
import com.leandro1995.seito.R
import com.leandro1995.seito.activity.NoteDetailActivity
import com.leandro1995.seito.component.list.config.callback.NoteVerticalComponentListCallBack
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.FragmentExamGraphicBinding
import com.leandro1995.seito.extension.lifecycleScope
import com.leandro1995.seito.extension.string
import com.leandro1995.seito.fragment.ambient.FragmentAmbient
import com.leandro1995.seito.intent.callback.action.ExamGraphicIntentActionCallBack
import com.leandro1995.seito.intent.callback.event.ExamGraphicIntentEventCallBack
import com.leandro1995.seito.intent.config.action.ExamGraphicIntentActionConfig
import com.leandro1995.seito.intent.config.event.ExamGraphicIntentEventConfig
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.model.entity.Answer
import com.leandro1995.seito.model.entity.Note
import com.leandro1995.seito.util.design.StudentPerformanceUtilDesign
import com.leandro1995.seito.util.dialog.AppUtilDialog
import com.leandro1995.seito.viewmodel.ExamGraphicViewModel

class ExamGraphicFragment : FragmentAmbient<FragmentExamGraphicBinding>(),
    ExamGraphicIntentEventCallBack, ExamGraphicIntentActionCallBack,
    NoteVerticalComponentListCallBack {

    private val examGraphicViewModel by viewModels<ExamGraphicViewModel>()

    private val examGraphicIntentEventConfig =
        ExamGraphicIntentEventConfig(examGraphicIntentEventCallBack = this)
    private val examGraphicIntentActionConfig =
        ExamGraphicIntentActionConfig(examGraphicIntentActionCallBack = this)

    override var idLayout: Int = R.layout.fragment_exam_graphic

    override fun initView() {
        dataBinding?.apply {
            examGraphicViewModel = this@ExamGraphicFragment.examGraphicViewModel
            noteVerticalComponentList.noteVerticalComponentListCallBack = this@ExamGraphicFragment
        }
    }

    override fun initEventToAction() {
        lifecycleScope {
            examGraphicViewModel.event.collect { examGraphicIntentEvent ->
                examGraphicIntentEventConfig.initConfig(event = examGraphicIntentEvent)
            }
        }

        lifecycleScope {
            examGraphicViewModel.action.collect { examGraphicIntentAction ->
                examGraphicIntentActionConfig.initConfig(event = examGraphicIntentAction)
            }
        }
    }

    override fun putExtra() {
        examGraphicViewModel.student.email =
            Setting.EMAIL_PUT_EXTRA.string(activity = requireActivity()).orEmpty()
    }

    override fun loading(loading: Loading) {
        dataBinding?.loadingComponent?.startService(loading = loading) {
            examGraphicViewModel.service(idService = loading.idService)
        }
    }

    override fun startService() {
        examGraphicViewModel.button.invoke(ExamGraphicViewModel.EXAM)
    }

    override fun noteArrayList(noteArrayList: ArrayList<Note>) {
        dataBinding?.examPieGraphicComponent?.graphic(
            titleCenter = getString(
                R.string.average_value_text,
                StudentPerformanceUtilDesign.promise(noteArrayList = noteArrayList)
            ), pieEntryArrayList = StudentPerformanceUtilDesign.totalPromise(
                context = requireContext(), noteArrayList = noteArrayList
            )
        )

        dataBinding?.noteVerticalComponentList?.setAdapter(arrayList = noteArrayList)
    }

    override fun alertMessage(alertMessage: AlertMessage) {
        AppUtilDialog.dialogMaterialDesign(
            context = requireContext(), alertMessage = alertMessage
        ) {
            requireActivity().finish()
        }
    }

    override fun noteDetail(note: Note, answerArrayList: ArrayList<Answer>) {
        startActivity(Intent(requireContext(), NoteDetailActivity::class.java).apply {
            putExtra(Setting.NOTE_PUT_EXTRA, note)
            putExtra(Setting.ANSWER_ARRAY_LIST_PUT_EXTRA, answerArrayList)
        })
    }

    override fun note(note: Note) {
        examGraphicViewModel.noteSelect(note = note)
    }
}