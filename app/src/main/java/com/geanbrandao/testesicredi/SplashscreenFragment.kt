package com.geanbrandao.testesicredi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.Navigation
import com.geanbrandao.testesicredi.databinding.FragmentSplashscreenBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SplashscreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashscreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashscreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashscreenBinding.inflate(inflater)

        createListeners()

        return binding.root
    }

    private fun createListeners() {
        binding.motionSplashscreen.addTransitionListener(object: MotionLayout.TransitionListener {
            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {}

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                goNext()
            }

            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}

        })
    }

    private fun goNext() {
        Navigation.findNavController(binding.root).navigate(R.id.action_splashscreenFragment_to_eventsFragment)
    }


}