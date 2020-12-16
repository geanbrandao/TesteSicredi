package com.geanbrandao.testesicredi.ui.details

import android.content.Context
import android.location.Address
import android.location.Geocoder
import androidx.lifecycle.ViewModel
import com.geanbrandao.testesicredi.R
import com.geanbrandao.testesicredi.data.CheckinRequest
import com.geanbrandao.testesicredi.repositories.EventsRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import java.io.IOException
import java.util.*


class DetailsEventViewModel(private val mRepository: EventsRepository) : ViewModel() {


    fun observeAddress(lat: Double, lng: Double, context: Context): Single<String> {
        return Single.create<String> { emitter ->
            try {
                val geocoder = Geocoder(context, Locale.getDefault())
                val addresses: List<Address> = geocoder.getFromLocation(lat, lng, 1)
                val address = addresses[0].getAddressLine(0)

                Timber.d("DEBUG1 - $address")

                emitter.onSuccess(address)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun postCheckIn(data: CheckinRequest): Completable {
        return mRepository.postCheckIn(data)
    }

    fun getAddress(lat: Double, lng: Double, context: Context): Single<String> {
        return Single.create { e: SingleEmitter<String> ->
            val geocoder = Geocoder(context, Locale.getDefault())
            try {
                val addresses =
                    geocoder.getFromLocation(lat, lng, 1)

                val address = addresses[0].getAddressLine(0) ?: context
                    .getString(R.string.text_error_message_error_find_address)

                e.onSuccess(address)
            } catch (e1: IOException) {
                e.onError(e1)
            }
        }

    }

}