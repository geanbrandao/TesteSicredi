package com.geanbrandao.testesicredi.repositories

import android.content.Context
import com.geanbrandao.testesicredi.model.Event
import io.reactivex.Single

interface EventsRepository {
    fun getEvents(context: Context): Single<ArrayList<Event>>
}