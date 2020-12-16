package com.geanbrandao.testesicredi.repositories

import android.content.Context
import com.geanbrandao.testesicredi.data.CheckinRequest
import com.geanbrandao.testesicredi.model.Event
import io.reactivex.Completable
import io.reactivex.Single

interface EventsRepository {
    fun getEvents(context: Context): Single<ArrayList<Event>>
    fun postCheckIn(data: CheckinRequest): Completable
}