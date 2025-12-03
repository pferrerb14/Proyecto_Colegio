package com.leandro1995.seito.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.leandro1995.seito.UserProtoDataStore
import com.leandro1995.seito.config.Setting
import com.leandro1995.seito.protodatastore.serializer.UserProtoDataStoreSerializer
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
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

fun String.argumentString(bundle: Bundle?): String? = bundle?.getString(this)

@Suppress("DEPRECATION")
inline fun <reified T> String.parcelable(activity: Activity): T? =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        activity.intent.getParcelableExtra(this, T::class.java)
    } else {
        activity.intent.getParcelableExtra(this) as? T
    }

fun String.boolean(activity: Activity): Boolean = activity.intent.getBooleanExtra(this, false)

fun String.capsSentences(): String {
    val splitArray =
        this.lowercase(Locale.getDefault()).split(" ".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
    val stringBuilder = StringBuilder()
    for (position in splitArray.indices) {
        val split = splitArray[position]
        if (position > 0 && split.isNotEmpty()) {
            stringBuilder.append(" ")
        }
        val value = (split.substring(0, 1).uppercase(Locale.getDefault()) + split.substring(1))
        stringBuilder.append(value)
    }

    return stringBuilder.toString()
}

fun Activity.youtubeStartActivity(url: String) {
    startActivity(Intent(Intent.ACTION_VIEW, url.toUri()))
}

fun visible(isVisible: Boolean) = if (!isVisible) {
    View.VISIBLE
} else {
    View.GONE
}

fun AppCompatActivity.launcher(success: () -> Unit) =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            success()
        }
    }

fun Fragment.launcher(success: () -> Unit) =
    registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            success()
        }
    }

@SuppressLint("SimpleDateFormat")
fun Date.format(format: String): String = SimpleDateFormat(format).format(this)