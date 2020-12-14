package com.geanbrandao.testesicredi.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.geanbrandao.testesicredi.R
import com.geanbrandao.testesicredi.data.EventsResponse
import com.geanbrandao.testesicredi.model.Event
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import timber.log.Timber
import java.lang.reflect.Array

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

//    fun getInfoJson(context: Context): EventsResponse? {
//        val moshi = Moshi.Builder()
//            .add(KotlinJsonAdapterFactory())
//            .build()
//
//        val adapter: JsonAdapter<EventsResponse> = moshi.adapter(EventsResponse::class.java)
//
//        val jsonString: String =
//            context.resources.openRawResource(R.raw.events).bufferedReader().use {
//                it.readText()
//            }
//        return adapter.fromJson(jsonString)
//    }
}