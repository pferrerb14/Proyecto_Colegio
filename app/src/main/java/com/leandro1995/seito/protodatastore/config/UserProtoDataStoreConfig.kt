package com.leandro1995.seito.protodatastore.config

import android.content.Context
import androidx.datastore.core.DataStore
import com.leandro1995.seito.UserProtoDataStore
import com.leandro1995.seito.extension.userProtoDataStore

object UserProtoDataStoreConfig {

    private var userProtoDataStore: DataStore<UserProtoDataStore>? = null

    fun instance(context: Context) {
        if (userProtoDataStore == null) {
            userProtoDataStore = context.userProtoDataStore
        }
    }
}