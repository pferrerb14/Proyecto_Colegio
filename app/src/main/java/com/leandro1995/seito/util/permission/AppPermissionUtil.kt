package com.leandro1995.seito.util.permission

import android.Manifest
import android.os.Build
import androidx.fragment.app.FragmentActivity
import com.leandro1995.seito.R
import com.permissionx.guolindev.PermissionX

object AppPermissionUtil {

    fun notificationPermission(activity: FragmentActivity, success: () -> Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            appPermission(
                activity = activity,
                permissionList = listOf(Manifest.permission.POST_NOTIFICATIONS),
                success = success
            )
        } else {
            success()
        }
    }

    private fun appPermission(
        activity: FragmentActivity, permissionList: List<String>, success: () -> Unit
    ) {
        PermissionX.init(activity).permissions(permissionList)
            .onExplainRequestReason { scope, deniedList ->
                scope.showRequestReasonDialog(
                    deniedList,
                    activity.getText(R.string.error_permission_message).toString(),
                    activity.getText(R.string.accept_button).toString(),
                    activity.getText(R.string.cancel_button).toString()
                )
            }.onForwardToSettings { scope, deniedList ->
                scope.showForwardToSettingsDialog(
                    deniedList,
                    activity.getText(R.string.deny_permits_again_permission_message).toString(),
                    activity.getText(R.string.accept_button).toString(),
                    activity.getText(R.string.cancel_button).toString()
                )
            }.request { isAllGranted, _, _ ->
                if (isAllGranted) {
                    success()
                }
            }
    }
}