package com.geanbrandao.testesicredi.module

import com.geanbrandao.testesicredi.model.Event
import com.geanbrandao.testesicredi.repositories.EventsRepository
import com.geanbrandao.testesicredi.repositories.EventsRepositoryImpl
import com.geanbrandao.testesicredi.ui.adapters.EventsAdapter
import com.geanbrandao.testesicredi.ui.details.DetailsEventViewModel
import com.geanbrandao.testesicredi.ui.events.EventsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { EventsViewModel(get()) }
    viewModel { DetailsEventViewModel(get()) }
}

val repositoryModule = module {
    single<EventsRepository> { EventsRepositoryImpl() }
}

val adapterModule = module {
    factory { (onClick: (event: Event) -> Unit) ->
        EventsAdapter(context = get(), onClick = onClick)
    }
}