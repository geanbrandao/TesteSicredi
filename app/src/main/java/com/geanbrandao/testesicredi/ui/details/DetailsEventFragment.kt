package com.geanbrandao.testesicredi.ui.details

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.doOnTextChanged
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.geanbrandao.testesicredi.*
import com.geanbrandao.testesicredi.data.CheckinRequest
import com.geanbrandao.testesicredi.databinding.DialogCheckinBinding
import com.geanbrandao.testesicredi.databinding.FragmentDetailsEventBinding
import com.geanbrandao.testesicredi.model.Event
import com.geanbrandao.testesicredi.ui.events.EventsViewModel
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.lang.StringBuilder
import java.util.*

/**
 * A simple [Fragment] subclass.
 * Use the [DetailsEventFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsEventFragment : Fragment() {

    private lateinit var binding: FragmentDetailsEventBinding

    private val args: DetailsEventFragmentArgs by navArgs()

    private val viewModel: DetailsEventViewModel by viewModel()

    private var disposable: Disposable? = null
    private var disposablePost: Disposable? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailsEventBinding.inflate(inflater)

        val event: Event? = args.keyEventArg

        createListeners()

        return return binding.root
    }

    private fun createListeners() {
        setupToolbar()
        setFields()
        getAddress()

        binding.buttonShare.setOnClickListener {
            val event: Event? = args.keyEventArg

            val builder = StringBuilder()
            builder.append(event?.title)
            builder.append(" - ")
            builder.append(getString(R.string.details_fragment_text_date, event?.date?.convertoToDateString()))
            builder.append(" - ")
            builder.append(getString(R.string.details_fragment_text_price, "%.2f".format(event?.price)))

            requireContext().openShareSheet(builder.toString())
        }

        binding.buttonCheckin.setOnClickListener {
            openDialogCheckin()
        }
    }

    private fun openDialogCheckin() {
        val binding = DialogCheckinBinding.inflate(this.layoutInflater)
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(binding.root)

        val alertDialog = builder.create()

        binding.buttonOk.setOnClickListener {
            binding.progressBar.show()
            val event: Event? = args.keyEventArg
            val data = CheckinRequest(event!!.id, binding.inputName.text.toString(), binding.inputEmail.text.toString())
            disposablePost = viewModel.postCheckIn(data).subscribeBy(
                onError = {
                    globalExceptionHandle(it)
                    binding.progressBar.hide()
                },
                onComplete = {
                    binding.progressBar.hide()
                    alertDialog.dismiss()
                }
            )
        }

        alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        alertDialog.show()
    }


    private fun setFields() {
        val eventArg: Event? = args.keyEventArg
        eventArg?.let { event ->
            binding.imageEvent.loadImage(event.image)
            binding.textTitleEvent.text = event.title
            binding.textDescription.text = event.description
            binding.textPrice.text = getString(R.string.details_fragment_text_price, "%.2f".format(event.price))
            binding.textDate.text = getString(R.string.details_fragment_text_date, event.date.convertoToDateString())
        }
    }

    private fun getAddress() {
        val eventArg: Event? = args.keyEventArg
        eventArg?.let { event ->
            disposable = viewModel.getAddress(event.latitude, event.longitude, requireContext())
                .subscribeBy(
                    onError = {
                        Timber.e(it)
                        // handle with error
                        binding.progressBar.hide()
                        binding.textLocalization.text = getString(R.string.details_fragment_text_address,
                            getString(R.string.text_error_message_error_find_address))
                        binding.textLocalization.show()
                    },
                    onSuccess = {
                        binding.textLocalization.text = getString(R.string.details_fragment_text_address, it)
                        binding.progressBar.hide()
                        binding.textLocalization.show()
                    }
                )
        }
    }

    private fun setupToolbar() {
        binding.toolbar.textTitle.text = getString(R.string.details_fragment_text_title_screen)
        binding.toolbar.iconBack.increaseHitArea(20f)
        binding.toolbar.iconBack.setOnClickListener {
            Navigation.findNavController(binding.root).navigateUp()
        }
    }

    override fun onStop() {
        super.onStop()
        disposable?.dispose()
        disposablePost?.dispose()
    }

}