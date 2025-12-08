package com.leandro1995.seito.retrofit.api

import com.leandro1995.seito.retrofit.config.Setting
import com.leandro1995.seito.retrofit.response.ChatResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface GetApi {

    @GET("api/solve")
    suspend fun solution(@Query(Setting.PROBLEM_TEXT) problemText: String): Response<ChatResponse>
}