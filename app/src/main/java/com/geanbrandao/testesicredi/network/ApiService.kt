package com.geanbrandao.testesicredi.network

import com.geanbrandao.testesicredi.model.Event
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {
    @GET("/api/events")
    fun getEvents(): Single<ArrayList<Event>>
}