package com.leandro1995.seito.activity

import com.leandro1995.seito.R
import com.leandro1995.seito.activity.ambient.ActivityAmbient
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.databinding.ActivityNoteDetailBinding
import com.leandro1995.seito.extension.parcelable
import com.leandro1995.seito.model.entity.Answer
import com.leandro1995.seito.model.entity.Note

class NoteDetailActivity : ActivityAmbient<ActivityNoteDetailBinding>() {

    override var idLayout: Int = R.layout.activity_note_detail

    override fun putExtra() {
        Setting.NOTE_PUT_EXTRA.parcelable<Note>(activity = this)?.let {
            dataBinding?.dateText?.text = getString(R.string.date_completed_text, it.date)
            dataBinding?.timerText?.text = getString(R.string.approximate_time_text, it.timer)
            dataBinding?.noteText?.text = getString(R.string.test_score_text, it.note)
        }

        Setting.ANSWER_ARRAY_LIST_PUT_EXTRA.parcelable<ArrayList<Answer>>(activity = this)?.let {
            dataBinding?.answerVerticalComponentList?.setAdapter(arrayList = it)
        }
    }
}