package com.leandro1995.seito.retrofit.config

import com.leandro1995.seito.retrofit.api.GetApi
import retrofit2.Retrofit

object RetrofitConfig {

    private val retrofit = Retrofit.Builder().baseUrl("").build()

    val getApi: GetApi = retrofit.create(GetApi::class.java)
}