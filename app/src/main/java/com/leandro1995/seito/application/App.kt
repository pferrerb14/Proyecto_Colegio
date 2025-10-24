package com.leandro1995.seito.application

import android.app.Application
import com.leandro1995.seito.protodatastore.config.UserProtoDataStoreConfig

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        UserProtoDataStoreConfig.instance(context = this)
    }
}