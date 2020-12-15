package com.geanbrandao.testesicredi.ui.events

import android.content.Context
import androidx.lifecycle.ViewModel
import com.geanbrandao.testesicredi.model.Event
import com.geanbrandao.testesicredi.repositories.EventsRepository
import com.geanbrandao.testesicredi.repositories.EventsRepositoryImpl
import io.reactivex.Single
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class EventsViewModel(private val mRepository: EventsRepository) : ViewModel() {

    fun getEvents(context: Context): Single<ArrayList<Event>> {
        return mRepository.getEvents(context)
    }
}