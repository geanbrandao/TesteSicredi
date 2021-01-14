package com.geanbrandao.testesicredi

import android.util.Log
import androidx.multidex.MultiDexApplication
import com.geanbrandao.testesicredi.module.adapterModule
import com.geanbrandao.testesicredi.module.networkModule
import com.geanbrandao.testesicredi.module.repositoryModule
import com.geanbrandao.testesicredi.module.viewModelModule
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber


class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        AppCenter.start(
            this, "2678871c-8310-42cd-b392-fb5c357026b4",
            Analytics::class.java, Crashes::class.java
        )

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(viewModelModule, repositoryModule, adapterModule, networkModule))
        }

        if (BuildConfig.DEBUG) {
            // print log if is in debug mode
            Timber.plant(Timber.DebugTree())
        } else {
            // report bugs in AppCenter
            Timber.plant(CrashReportingTree())
        }
    }
}

private class CrashReportingTree : Timber.Tree() {
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        val exception = t ?: Exception(message)

        val properties: Map<String, String> = mapOf(
            KEY_PRIORITY to priority.toString(),
            KEY_TAG to (tag ?: ""),
            KEY_MESSAGE to message
        )

        Crashes.trackError(exception, properties, null)
    }

    companion object {
        private const val KEY_PRIORITY = "priority"
        private const val KEY_TAG = "tag"
        private const val KEY_MESSAGE = "message"
    }

}