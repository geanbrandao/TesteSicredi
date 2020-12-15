package com.geanbrandao.testesicredi.network

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

    private var gson = GsonBuilder().setLenient()
        .create()

    var retrofit = Retrofit.Builder()
        .baseUrl("https://5f5a8f24d44d640016169133.mockapi.io/")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    fun createService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}