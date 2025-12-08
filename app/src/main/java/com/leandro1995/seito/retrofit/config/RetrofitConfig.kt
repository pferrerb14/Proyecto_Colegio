package com.leandro1995.seito.retrofit.config

import com.leandro1995.seito.BuildConfig
import com.leandro1995.seito.retrofit.api.GetApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitConfig {

    private val retrofit = Retrofit.Builder().baseUrl(BuildConfig.URL_BASE)
        .addConverterFactory(GsonConverterFactory.create()).build()

    val getApi: GetApi = retrofit.create(GetApi::class.java)
}