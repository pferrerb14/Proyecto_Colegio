package com.leandro1995.seito.component.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

class NetworkUtil(private val context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val isActiveNetwork = connectivityManager.activeNetwork

    private val isNetworkCapabilities = connectivityManager.getNetworkCapabilities(isActiveNetwork)

    fun isInternetAvailable(): Boolean = when {
        isNetworkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ?: false -> true
        isNetworkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ?: false -> true
        isNetworkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ?: false -> true
        else -> false
    }
}