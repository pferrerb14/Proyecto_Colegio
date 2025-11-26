package com.leandro1995.seito.util.trustedtime

import android.content.Context
import com.google.android.gms.time.TrustedTime
import com.google.android.gms.time.TrustedTimeClient
import com.leandro1995.seito.extension.format
import java.util.Date

object TrustedTime {

    private var trustedTimeClient: TrustedTimeClient? = null

    fun init(context: Context) {
        TrustedTime.createClient(context).addOnSuccessListener {
            trustedTimeClient = it
        }.addOnFailureListener {
            trustedTimeClient = null
        }
    }

    fun date(format: String) =
        Date(trustedTimeClient?.computeCurrentUnixEpochMillis() ?: 0L).format(format = format)
}