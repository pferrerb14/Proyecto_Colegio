package com.leandro1995.seito.protodatastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.google.protobuf.InvalidProtocolBufferException
import com.leandro1995.seito.UserProtoDataStore
import java.io.InputStream
import java.io.OutputStream

object UserProtoDataStoreSerializer : Serializer<UserProtoDataStore> {

    override suspend fun readFrom(input: InputStream): UserProtoDataStore {
        try {
            return UserProtoDataStore.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("", exception)
        }
    }

    override suspend fun writeTo(t: UserProtoDataStore, output: OutputStream) = t.writeTo(output)

    override val defaultValue: UserProtoDataStore = UserProtoDataStore.getDefaultInstance()
}