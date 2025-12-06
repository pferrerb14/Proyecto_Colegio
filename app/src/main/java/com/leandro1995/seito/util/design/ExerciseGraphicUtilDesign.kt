package com.leandro1995.seito.util.design

import android.content.Context
import com.github.mikephil.charting.data.PieEntry
import com.leandro1995.seito.R
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.model.entity.Note

class ExerciseGraphicUtilDesign {

    companion object {

        fun totalPromise(context: Context, noteArrayList: ArrayList<Note>): ArrayList<PieEntry> {
            val pieEntryArray = ArrayList<PieEntry>()
            val percentage =
                (promise(noteArrayList = noteArrayList) / Setting.NOTE_MAXIMUM) * PERCENTAGE

            if (noteArrayList.isNotEmpty()) {
                pieEntryArray.add(
                    PieEntry(
                        percentage.toFloat(), context.getString(R.string.remaining_tab_text)
                    )
                )
                pieEntryArray.add(
                    PieEntry(
                        (PERCENTAGE - percentage).toFloat(),
                        context.getString(R.string.average_text)
                    )
                )
            }

            return pieEntryArray
        }

        fun promise(noteArrayList: ArrayList<Note>) =
            noteArrayList.sumOf { it.note } / noteArrayList.size

        private const val PERCENTAGE = 100
    }
}