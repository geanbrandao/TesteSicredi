package com.geanbrandao.testesicredi.repositories

import android.content.Context
import com.geanbrandao.testesicredi.R
import com.geanbrandao.testesicredi.data.CheckinRequest
import com.geanbrandao.testesicredi.data.EventsResponse
import com.geanbrandao.testesicredi.model.Event
import com.geanbrandao.testesicredi.network.ApiService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class EventsRepositoryImpl(
    private val api: ApiService
): EventsRepository {
    override fun getEvents(context: Context): Single<ArrayList<Event>> {
        return api.getEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun postCheckIn(data: CheckinRequest): Completable {
//        val data = CheckinRequest(1, "Gean", "gean.kar@gmail.com")
        return api.postCheckIn(data)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}