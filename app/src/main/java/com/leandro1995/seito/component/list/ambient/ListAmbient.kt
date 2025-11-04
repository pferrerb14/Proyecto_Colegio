package com.leandro1995.seito.component.list.ambient

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import com.leandro1995.seito.R
import com.leandro1995.seito.component.ambient.ComponentAmbient
import com.leandro1995.seito.component.util.TypeArrayUtil
import com.leandro1995.seito.databinding.ComponentListAmbientBinding

class ListAmbient(context: Context, attrs: AttributeSet? = null) :
    ComponentAmbient<ComponentListAmbientBinding>(context, attrs) {

    override var idLayout: Int = R.layout.component_list_ambient

    init {
        onCreateView()
        typeArrayView(
            typedArray = typeArray(
                attrs = attrs, idStyleableRes = R.styleable.ListAmbient
            )
        )
        messageErrorVisibility(arrayList = arrayListOf<String>())
    }

    override fun typeArrayView(typedArray: TypedArray?) {
        typedArray?.let {
            dataBinding?.errorMessageImageView?.setImageResource(
                TypeArrayUtil.imageTypeArray(
                    idStyleableRes = R.styleable.ListAmbient_error_image, typedArray = it
                )
            )
            dataBinding?.errorTitleText?.text = TypeArrayUtil.textTypeArray(
                idStyleableRes = R.styleable.ListAmbient_error_title, typedArray = it
            )
            dataBinding?.errorSubTitleText?.text = TypeArrayUtil.textTypeArray(
                idStyleableRes = R.styleable.ListAmbient_error_sub_title, typedArray = it
            )
            dataBinding?.errorListMaterialCardView?.setCardBackgroundColor(
                TypeArrayUtil.colorTypeArray(
                    idStyleableRes = R.styleable.ListAmbient_error_color, typedArray = it
                )
            )
        }
    }

    private fun messageErrorVisibility(arrayList: ArrayList<*>) {
        if (arrayList.isEmpty()) {
            dataBinding?.errorListMaterialCardView?.visibility = VISIBLE
            dataBinding?.listAmbientRecycler?.visibility = GONE
        } else {
            dataBinding?.errorListMaterialCardView?.visibility = GONE
            dataBinding?.listAmbientRecycler?.visibility = VISIBLE
        }
    }
}