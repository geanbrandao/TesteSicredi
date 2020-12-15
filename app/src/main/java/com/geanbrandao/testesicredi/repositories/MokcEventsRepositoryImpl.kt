package com.geanbrandao.testesicredi.repositories

import android.content.Context
import com.geanbrandao.testesicredi.R
import com.geanbrandao.testesicredi.data.EventsResponse
import com.geanbrandao.testesicredi.model.Event
import com.geanbrandao.testesicredi.network.ApiService
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MokcEventsRepositoryImpl(val api: ApiService): EventsRepository {

    override fun getEvents(context: Context): Single<ArrayList<Event>> {
        return Single.just(
            getInfoJson(context)?.let {
                it.toCollection(arrayListOf())
            } ?: run {
                arrayListOf()
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getInfoJson(context: Context): List<Event>? {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val listType = Types.newParameterizedType(List::class.java, Event::class.java)
        val adapter: JsonAdapter<List<Event>> = moshi.adapter(listType)

        val jsonString: String =
            context.resources.openRawResource(R.raw.events).bufferedReader().use {
                it.readText()
            }
        return adapter.fromJson(jsonString)
    }
}