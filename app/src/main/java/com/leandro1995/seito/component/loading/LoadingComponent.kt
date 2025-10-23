package com.leandro1995.seito.component.loading

import android.content.Context
import android.util.AttributeSet
import com.leandro1995.seito.R
import com.leandro1995.seito.background.coroutine.BackGroundCoroutine
import com.leandro1995.seito.background.coroutine.setting.TimeTypeCoroutine
import com.leandro1995.seito.component.ambient.ComponentAmbient
import com.leandro1995.seito.component.model.Loading
import com.leandro1995.seito.component.util.NetworkUtil
import com.leandro1995.seito.databinding.ComponentLoadingBinding
import com.leandro1995.seito.model.design.AlertMessage
import com.leandro1995.seito.util.dialog.AppUtilDialog

class LoadingComponent(context: Context, attrs: AttributeSet? = null) :
    ComponentAmbient<ComponentLoadingBinding>(context, attrs) {

    private val networkUtil = NetworkUtil(context = context)
    private val backGroundCoroutine =
        BackGroundCoroutine(time = TIME_LONG, timeTypeCoroutine = TimeTypeCoroutine.SECONDS)

    override var idLayout: Int = R.layout.component_loading

    init {
        onCreateView()
    }

    fun startService(idService: Int, method: suspend () -> Unit) {
        if (networkUtil.isInternetAvailable()) {
            visibility(isVisible = true)
            backGroundCoroutine.start {
                method()
                visibility(isVisible = false)
            }
        } else {
            AppUtilDialog.dialogMaterialDesign(
                context = context,
                alertMessage = AlertMessage(idMessage = R.string.internet_connection_message)
            )
        }
    }

    override fun visibility(isVisible: Boolean) {
        dataBinding?.loadingConstraint?.visibility = if (isVisible) VISIBLE else GONE
    }

    companion object {
        private const val TIME_LONG = 5L
    }
}