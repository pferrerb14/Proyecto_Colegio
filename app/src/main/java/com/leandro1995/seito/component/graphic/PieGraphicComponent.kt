package com.leandro1995.seito.component.graphic

import android.content.Context
import android.util.AttributeSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import com.leandro1995.seito.R
import com.leandro1995.seito.component.ambient.ComponentAmbient
import com.leandro1995.seito.databinding.ComponentGraphicPieBinding

class PieGraphicComponent(context: Context, attrs: AttributeSet? = null) :
    ComponentAmbient<ComponentGraphicPieBinding>(context, attrs) {

    override var idLayout: Int = R.layout.component_graphic_pie

    init {
        onCreateView()
    }

    fun graphic(pieEntryArrayList: ArrayList<PieEntry>) {
        dataBinding?.pieChart?.apply {
            data = pieData(pieDataSet = pieDataSet(pieEntryArrayList = pieEntryArrayList).apply {
                this.colors = ColorTemplate.MATERIAL_COLORS.toList()
            })
            description.isEnabled = false
            invalidate()
        }
    }

    private fun pieDataSet(pieEntryArrayList: ArrayList<PieEntry>) =
        PieDataSet(pieEntryArrayList, "")

    private fun pieData(pieDataSet: PieDataSet) = PieData(pieDataSet)
}