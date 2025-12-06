package com.leandro1995.seito.util.design

import com.github.mikephil.charting.data.PieEntry
import com.leandro1995.seito.model.entity.Note

class ExerciseGraphicUtilDesign {

    companion object {

        fun totalPromise(noteArrayList: ArrayList<Note>) =
            PieEntry((noteArrayList.sumOf { it.note }.toFloat() / noteArrayList.size))
    }
}