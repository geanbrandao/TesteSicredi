package com.geanbrandao.testesicredi.module

import android.content.Context
import com.geanbrandao.testesicredi.model.Event
import com.geanbrandao.testesicredi.network.ApiService
import com.geanbrandao.testesicredi.network.RetrofitInitializer
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
    single<EventsRepository> { EventsRepositoryImpl(get()) }
}

val adapterModule = module {
    // aqui nao foi passado o get, pois iria passar o 'applicationContext' para o adapter.
    // e com esse contexto o adapter nao funciona
    // como solucao foi passado um contexto via parametro, que eh injetado quando a classe eh declarada dentro da activity/fragment
    factory { (context: Context, onClick: (event: Event) -> Unit) ->
        EventsAdapter(context = context, onClick = onClick)
    }
}
val networkModule = module {
    single {
        RetrofitInitializer(get()).createService()
    }
}
