package com.geanbrandao.testesicredi.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.geanbrandao.testesicredi.R
import com.geanbrandao.testesicredi.databinding.FragmentEventsBinding
import com.geanbrandao.testesicredi.hide
import com.geanbrandao.testesicredi.model.Event
import com.geanbrandao.testesicredi.ui.adapters.EventsAdapter
import io.reactivex.rxkotlin.subscribeBy
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Use the [EventsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EventsFragment : Fragment() {

    private val viewModel: EventsViewModel by viewModel()

    private val adapter: EventsAdapter by inject {
        parametersOf(onClick)
    }

    val onClick: (item: Event) -> Unit = { item ->
        itemClicked(item)
    }

    private lateinit var binding: FragmentEventsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentEventsBinding.inflate(inflater)

        binding.recyclerEvents.adapter = adapter

        createListeners()

        return binding.root
    }

    private fun createListeners() {
        setupToolbar()
        getEvents()
    }

    private fun setupToolbar() {
        binding.toolbar.iconBack.hide()
        binding.toolbar.textTitle.text = getString(R.string.events_fragment_text_title_screen)
    }

    private fun getEvents() {
        val disposable = viewModel.getEvents(requireContext()).subscribeBy(
            onError = {
                  Timber.e(it)
                // TODO handle with api exeption
            },
            onSuccess = {
                // fill the adapter
                adapter.addAll(it)
            }
        )
    }

    private fun itemClicked(item: Event) {
        goNext()
    }

    private fun goNext() {
        Navigation.findNavController(binding.root).navigate(R.id.action_eventsFragment_to_detailsEventFragment)
    }
}