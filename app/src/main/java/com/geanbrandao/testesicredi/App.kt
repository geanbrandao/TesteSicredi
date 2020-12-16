package com.geanbrandao.testesicredi

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
            // TODO create crash report using appCenter
        }
    }
}