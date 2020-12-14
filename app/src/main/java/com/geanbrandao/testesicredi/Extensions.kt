package com.geanbrandao.testesicredi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.geanbrandao.testesicredi.data.EventsResponse
import com.geanbrandao.testesicredi.model.Event
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.text.SimpleDateFormat
import java.util.*

fun Activity.goToActivity(activityClass: Class<*>) {
    startActivity(Intent(this, activityClass))
}

fun Long.convertToDate(): String {
    val date = Date(this)
    val format = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return format.format(date)
}

fun Long.convertoToDateString(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val date = Date(this * 1000L)
    return sdf.format(date)
}

fun AppCompatImageView.loadImage(url: String) {
    Glide.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(R.drawable.ic_image)
        .error(R.drawable.ic_broken_image)
        .into(this)
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}