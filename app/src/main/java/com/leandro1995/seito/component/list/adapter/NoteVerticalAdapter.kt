package com.leandro1995.seito.component.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.leandro1995.seito.R
import com.leandro1995.seito.component.list.adapter.viewholder.NoteVerticalViewHolder
import com.leandro1995.seito.component.list.config.callback.adapter.NoteVerticalAdapterCallBack
import com.leandro1995.seito.component.list.config.callback.adapter.ambient.ListAmbientOnclick
import com.leandro1995.seito.component.list.model.Note
import com.leandro1995.seito.databinding.ItemNoteVerticalBinding

class NoteVerticalAdapter(
    private val context: Context, private val noteArrayList: ArrayList<Note>
) : RecyclerView.Adapter<NoteVerticalViewHolder>(), ListAmbientOnclick {

    var noteVerticalAdapterCallBack: NoteVerticalAdapterCallBack? = null

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): NoteVerticalViewHolder {
        return NoteVerticalViewHolder(
            itemNoteVerticalBinding = ItemNoteVerticalBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), listAmbientOnclick = this
        )
    }

    override fun onBindViewHolder(
        holder: NoteVerticalViewHolder, position: Int
    ) {
        holder.itemNoteVerticalBinding.apply {
            dateText.text =
                context.getString(R.string.date_completed_text, noteArrayList[position].date)
            timerText.text =
                context.getString(R.string.approximate_time_text, noteArrayList[position].timer)
            noteText.text =
                context.getString(R.string.test_score_text, noteArrayList[position].note)
        }
    }

    override fun getItemCount(): Int {
        return noteArrayList.size
    }

    override fun onclick(position: Int) {
        noteVerticalAdapterCallBack?.note(note = noteArrayList[position])
    }
}