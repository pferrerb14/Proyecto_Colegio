package com.leandro1995.seito.extension

import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.leandro1995.seito.UserProtoDataStore
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.protodatastore.serializer.UserProtoDataStoreSerializer
import kotlinx.coroutines.launch
import java.util.regex.Pattern

fun <T : ViewDataBinding> Activity.binding(@LayoutRes idLayout: Int): T? =
    DataBindingUtil.setContentView<T>(this, idLayout)

fun <T : ViewDataBinding> ViewGroup.binding(context: Context, @LayoutRes idLayout: Int): T? =
    DataBindingUtil.inflate<T>(LayoutInflater.from(context), idLayout, this, true)

fun <T : ViewDataBinding> bindingFragment(
    inflater: LayoutInflater, @LayoutRes idLayout: Int, container: ViewGroup?
): T? = DataBindingUtil.inflate<T>(inflater, idLayout, container, false)

fun Activity.lifecycleScope(method: suspend () -> Unit) {
    (this as AppCompatActivity).lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            method()
        }
    }
}

fun Fragment.lifecycleScope(method: suspend () -> Unit) {
    viewLifecycleOwner.lifecycleScope.launch {
        viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            method()
        }
    }
}

fun String.isEmailFormat() = Pattern.compile(Setting.EMAIL_REGEX).matcher(this).matches()

val Context.userProtoDataStore: DataStore<UserProtoDataStore> by dataStore(
    fileName = Setting.NAME_FILE_DATA_STORE, serializer = UserProtoDataStoreSerializer
)

@Suppress("DEPRECATION")
inline fun <reified T> String.argumentParcelable(bundle: Bundle?): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        bundle?.getParcelable(this, T::class.java)
    } else {
        bundle?.getParcelable(this) as? T
    }