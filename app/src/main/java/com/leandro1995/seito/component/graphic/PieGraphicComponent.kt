package com.leandro1995.seito.component.graphic

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.leandro1995.seito.R
import com.leandro1995.seito.component.ambient.ComponentAmbient
import com.leandro1995.seito.component.util.TypeArrayUtil
import com.leandro1995.seito.databinding.ComponentGraphicPieBinding

class PieGraphicComponent(context: Context, attrs: AttributeSet? = null) :
    ComponentAmbient<ComponentGraphicPieBinding>(context, attrs) {

    override var idLayout: Int = R.layout.component_graphic_pie

    init {
        onCreateView()
        typeArrayView(
            typedArray = typeArray(
                attrs = attrs, idStyleableRes = R.styleable.PieGraphicComponent
            )
        )
        visibility(isVisible = false)
    }

    override fun typeArrayView(typedArray: TypedArray?) {
        typedArray?.let {
            dataBinding?.titleText?.text = TypeArrayUtil.textTypeArray(
                idStyleableRes = R.styleable.PieGraphicComponent_title_graphic, typedArray = it
            )

            dataBinding?.messageErrorText?.text = TypeArrayUtil.textTypeArray(
                idStyleableRes = R.styleable.PieGraphicComponent_message_error, typedArray = it
            )
        }
    }

    override fun visibility(isVisible: Boolean) {
        if (isVisible) {
            dataBinding?.pieChart?.visibility = VISIBLE
            dataBinding?.messageErrorText?.visibility = GONE
        } else {
            dataBinding?.pieChart?.visibility = GONE
            dataBinding?.messageErrorText?.visibility = VISIBLE
        }
    }

    fun graphic(titleCenter: String = "", pieEntryArrayList: ArrayList<PieEntry>) {
        visibility(isVisible = pieEntryArrayList.isNotEmpty())
        dataBinding?.pieChart?.apply {
            data = pieData(pieDataSet = pieDataSet(pieEntryArrayList = pieEntryArrayList))
            description.isEnabled = false
            setUsePercentValues(true)
            holeRadius = 40f
            isRotationEnabled = false
            transparentCircleRadius = 50f
            setDrawCenterText(true)
            if (titleCenter.isNotEmpty()) {
                centerText = titleCenter
            }
            setCenterTextSize(15f)
            invalidate()
        }
    }

    private fun pieDataSet(pieEntryArrayList: ArrayList<PieEntry>) =
        PieDataSet(pieEntryArrayList, "").apply {
            colors = listOf(
                ContextCompat.getColor(context, R.color.blue_6EC5FF),
                ContextCompat.getColor(context, R.color.green_9EE6B8),
                ContextCompat.getColor(context, R.color.red_FF9CA3),
                ContextCompat.getColor(context, R.color.yellow_FFE89A),
                ContextCompat.getColor(context, R.color.orange_FFC9A3),
                ContextCompat.getColor(context, R.color.purple_D7C5FF)
            )

            valueTextColor = ContextCompat.getColor(context, R.color.white_FFFFFF)
            valueTextSize = 16f
            valueFormatter = PercentFormatter(dataBinding?.pieChart)
        }

    private fun pieData(pieDataSet: PieDataSet) = PieData(pieDataSet)
}