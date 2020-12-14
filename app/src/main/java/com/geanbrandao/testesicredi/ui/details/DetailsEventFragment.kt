package com.geanbrandao.testesicredi.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.geanbrandao.testesicredi.R
import com.geanbrandao.testesicredi.databinding.FragmentDetailsEventBinding

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsEventFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsEventFragment : Fragment() {

    private lateinit var binding: FragmentDetailsEventBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsEventBinding.inflate(inflater)

        createListeners()

        return return binding.root
    }

    private fun createListeners() {
        setupToolbar()
    }

    private fun setupToolbar() {
        binding.toolbar.textTitle.text = getString(R.string.details_fragment_text_title_screen)
        binding.toolbar.iconBack.setOnClickListener {
            Navigation.findNavController(binding.root).navigateUp()
        }
    }


}