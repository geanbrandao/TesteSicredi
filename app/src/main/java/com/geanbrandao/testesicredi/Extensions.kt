package com.geanbrandao.testesicredi

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.TypedValue
import android.view.TouchDelegate
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.geanbrandao.testesicredi.data.ErrorResponse
import com.geanbrandao.testesicredi.databinding.CustomErrorDialogBinding
import com.geanbrandao.testesicredi.network.RetrofitInitializer
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import okhttp3.ResponseBody
import org.koin.android.ext.android.inject
import retrofit2.Converter
import timber.log.Timber
import java.net.ConnectException
import java.net.UnknownHostException
import java.text.SimpleDateFormat
import java.util.*

fun Long.convertoToDateString(): String {
    val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    sdf.timeZone = TimeZone.getTimeZone("UTC+3")
    val date = Date(this * 1000L)
    return sdf.format(date)
}

fun AppCompatImageView.loadImage(url: String) {

    val iconError = this.context.getIcon(R.drawable.ic_broken_image, R.color.color_window_icon)
    val iconPlaceholder = this.context.getIcon(R.drawable.ic_image, R.color.color_window_icon)

    Glide.with(this.context)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .placeholder(iconPlaceholder)
        .error(iconError)
        .into(this)
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.increaseHitArea(dp: Float) {
    // increase the hit area
    val increasedArea = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        Resources.getSystem().displayMetrics
    ).toInt()
    val parent = parent as View
    parent.post {
        val rect = Rect()
        getHitRect(rect)
        rect.top -= increasedArea
        rect.left -= increasedArea
        rect.bottom += increasedArea
        rect.right += increasedArea
        parent.touchDelegate = TouchDelegate(rect, this)
    }
}

fun AppCompatActivity.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.getIcon(iconId: Int, colorId: Int): Drawable? {
    val icon = ContextCompat.getDrawable(this, iconId)
    icon?.let {
        val h: Int = it.intrinsicHeight
        val w: Int = it.intrinsicWidth
        it.setBounds(0, 0, w, h)
        it.setTint(ContextCompat.getColor(this, colorId))
        return icon
    } ?: run {
        return null
    }
}

// dialog de erro comum
fun Activity.showDialogError(message: String): AlertDialog {
    val binding = CustomErrorDialogBinding.inflate(this.layoutInflater)
    val builder = AlertDialog.Builder(this)
    builder.setView(binding.root)
    val alertDialog = builder.create()

    if (message.isNotEmpty()) {
        binding.text1.text = message
    }

    binding.okBtn.setOnClickListener {
        alertDialog.dismiss()
    }

    alertDialog.setCanceledOnTouchOutside(false)
    alertDialog.setCancelable(false)

    alertDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

    alertDialog.show()

    return alertDialog
}

fun Fragment.globalExceptionHandle(error: Throwable): AlertDialog {
    val tag = this::class.java.simpleName
    val retrofitInitializer: RetrofitInitializer by inject()
    Timber.tag(tag).e(error)
    if (error is HttpException) {
        if (error.code() > 499) {
            return this.requireActivity().showDialogError("Server error")
        } else {
            if (error.code() in 400..499) {
                if (error.code() == 401) {
                    return this.requireActivity().showDialogError("Sessão expirada")
                }
                val body = error.response().errorBody()
                val errorConverter: Converter<ResponseBody, ErrorResponse> =
                    retrofitInitializer.retrofit.responseBodyConverter(
                        ErrorResponse::class.java,
                        arrayOf()
                    )
                body?.let {
                    val converted = errorConverter.convert(body)
                    return this.requireActivity().showDialogError(converted.error)
                } ?: run {
                    return this.requireActivity().showDialogError(getString(R.string.errors_generic))
                }
            }
        }
    }

    if (error is UnknownHostException || error is ConnectException) {
        return this.requireActivity().showDialogError(getString(R.string.errors_no_connection))
    }

    return this.requireActivity().showDialogError(getString(R.string.errors_generic))
}

fun Context.isNetworkAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        if (capabilities != null) {
            when {
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                    return true
                }
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                    return true
                }
            }
        }
    } else {
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
            return true
        }
    }
    return false
}

fun Context.openShareSheet(message: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(
            Intent.EXTRA_TEXT,
            message
        )
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Compartilhar via")
    shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(this, shareIntent, null)
}