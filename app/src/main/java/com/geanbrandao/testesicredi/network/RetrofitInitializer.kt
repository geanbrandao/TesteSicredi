package com.geanbrandao.testesicredi.network

import android.content.Context
import com.geanbrandao.testesicredi.isNetworkAvailable
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

class RetrofitInitializer(context: Context) {

    companion object {
        const val BASE_URL = "https://5f5a8f24d44d640016169133.mockapi.io/"
        const val HEADER_CACHE_CONTROL = "Cache-Control"
        const val HEADER_PRAGMA = "Pragma"
    }

    private val cacheSize = (5 * 1024 * 1024).toLong()

    private var gson = GsonBuilder().setLenient()
        .create()

    var retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient(context))
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    private fun okHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(Cache(context.cacheDir, cacheSize))
            .addNetworkInterceptor(networkInterceptor()) // only used when network is on
            .addInterceptor(offlineInterceptor(context))
            .build()
    }

    private fun offlineInterceptor(context: Context): Interceptor {
        return Interceptor { chain ->
            Timber.d("offline interceptor: called.")
            var request = chain.request()

            // prevent caching when network is on. For that we use the "networkInterceptor"
            if (!context.isNetworkAvailable()) {

                val cacheControl = CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()

                request = request.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }

            return@Interceptor chain.proceed(request)
        }
    }

    private fun networkInterceptor(): Interceptor {
        return Interceptor { chain ->
            Timber.d("network interceptor: called.")
            val response: Response = chain.proceed(chain.request())

            val cacheControl = CacheControl.Builder()
                .maxAge(5, TimeUnit.SECONDS)
                .build()

            return@Interceptor response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    fun createService(): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}