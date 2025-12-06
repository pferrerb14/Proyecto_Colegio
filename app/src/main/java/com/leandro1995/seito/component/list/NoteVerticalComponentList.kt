package com.leandro1995.seito.component.list

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.component.list.adapter.NoteVerticalAdapter
import com.leandro1995.seito.component.list.ambient.ComponentListAmbient
import com.leandro1995.seito.component.list.config.callback.NoteVerticalComponentListCallBack
import com.leandro1995.seito.component.list.config.callback.adapter.NoteVerticalAdapterCallBack
import com.leandro1995.seito.component.list.model.Note

class NoteVerticalComponentList(context: Context, attrs: AttributeSet? = null) :
    ComponentListAmbient(context, attrs), NoteVerticalAdapterCallBack {

    private var noteArrayList: ArrayList<Note>? = null
    private var noteVerticalAdapter: NoteVerticalAdapter? = null

    var noteVerticalComponentListCallBack: NoteVerticalComponentListCallBack? = null

    init {
        onCreateViewList()
    }

    override fun onCreateViewList() {
        noteArrayList = arrayListOf()
        noteVerticalAdapter = noteArrayList?.let {
            NoteVerticalAdapter(context = context, noteArrayList = it).apply {
                noteVerticalAdapterCallBack = this@NoteVerticalComponentList
            }
        }

        noteVerticalAdapter?.let {
            recyclerViewLayout(recyclerViewAdapter = it)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun setAdapter(arrayList: ArrayList<*>) {
        messageErrorVisibility(arrayList = arrayList)

        noteArrayList?.clear()
        arrayList.forEach {
            (it as com.leandro1995.seito.model.entity.Note).let { note ->
                noteArrayList?.add(
                    Note(
                        date = note.date,
                        idGroup = note.idGroup,
                        note = note.note,
                        timer = note.timer
                    )
                )
            }
        }

        noteVerticalAdapter?.notifyDataSetChanged()
    }

    override fun note(note: Note) {
        noteVerticalComponentListCallBack?.note(
            note = com.leandro1995.seito.model.entity.Note(
                date = note.date, idGroup = note.idGroup, note = note.note, timer = note.timer
            )
        )
    }
}