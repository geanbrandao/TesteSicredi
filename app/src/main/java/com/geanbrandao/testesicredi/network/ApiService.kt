package com.geanbrandao.testesicredi.network

import com.geanbrandao.testesicredi.data.CheckinRequest
import com.geanbrandao.testesicredi.model.Event
import io.reactivex.Completable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("/api/events")
    fun getEvents(): Single<ArrayList<Event>>

    @POST("/api/checkin")
    fun postCheckIn(@Body data: CheckinRequest): Completable
}