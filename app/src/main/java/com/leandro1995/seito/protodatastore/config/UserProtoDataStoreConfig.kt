package com.leandro1995.seito.protodatastore.config

import android.content.Context
import androidx.datastore.core.DataStore
import com.leandro1995.seito.UserProtoDataStore
import com.leandro1995.seito.extension.userProtoDataStore
import kotlinx.coroutines.flow.first

object UserProtoDataStoreConfig {

    private var userProtoDataStore: DataStore<UserProtoDataStore>? = null

    fun instance(context: Context) {
        if (userProtoDataStore == null) {
            userProtoDataStore = context.userProtoDataStore
        }
    }

    suspend fun setName(name: String) {
        userProtoDataStore?.updateData { setting ->
            setting.toBuilder().setName(name).build()
        }
    }

    suspend fun setLastName(lastName: String) {
        userProtoDataStore?.updateData { setting ->
            setting.toBuilder().setLastName(lastName).build()
        }
    }

    suspend fun setEmail(email: String) {
        userProtoDataStore?.updateData { setting ->
            setting.toBuilder().setEmail(email).build()
        }
    }

    suspend fun setSex(sex: String) {
        userProtoDataStore?.updateData { setting ->
            setting.toBuilder().setSex(sex).build()
        }
    }

    suspend fun setCode(code: String) {
        userProtoDataStore?.updateData { setting ->
            setting.toBuilder().setCode(code).build()
        }
    }

    suspend fun setNameTeacher(nameTeacher: String) {
        userProtoDataStore?.updateData { setting ->
            setting.toBuilder().setNameTeacher(nameTeacher).build()
        }
    }

    suspend fun setCoins(coins: Int) {
        userProtoDataStore?.updateData { setting ->
            setting.toBuilder().setCoins(coins).build()
        }
    }

    suspend fun setAge(age: Int) {
        userProtoDataStore?.updateData { setting ->
            setting.toBuilder().setAge(age).build()
        }
    }

    suspend fun setIsUserType(isUserType: Boolean) {
        userProtoDataStore?.updateData { setting ->
            setting.toBuilder().setIsUserType(isUserType).build()
        }
    }

    suspend fun getName(): String {
        return userProtoDataStore?.data?.first()?.name.orEmpty()
    }

    suspend fun getLastName(): String {
        return userProtoDataStore?.data?.first()?.lastName.orEmpty()
    }

    suspend fun getEmail(): String {
        return userProtoDataStore?.data?.first()?.email.orEmpty()
    }

    suspend fun getAge(): Int {
        return userProtoDataStore?.data?.first()?.age ?: -1
    }

    suspend fun getSex(): String {
        return userProtoDataStore?.data?.first()?.sex.orEmpty()
    }

    suspend fun getCode(): String {
        return userProtoDataStore?.data?.first()?.code.orEmpty()
    }

    suspend fun getNameTeacher(): String {
        return userProtoDataStore?.data?.first()?.nameTeacher.orEmpty()
    }

    suspend fun getCoins(): Int {
        return userProtoDataStore?.data?.first()?.coins ?: -1
    }

    suspend fun getIsUserType(): Boolean {
        return userProtoDataStore?.data?.first()?.isUserType ?: false
    }
}