package com.leandro1995.seito.application

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.leandro1995.seito.protodatastore.config.UserProtoDataStoreConfig
import com.leandro1995.seito.util.trustedtime.TrustedTime

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        UserProtoDataStoreConfig.instance(context = this)
        Fresco.initialize(this)
        TrustedTime.init(context = this)
    }
}