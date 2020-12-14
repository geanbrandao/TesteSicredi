package com.geanbrandao.testesicredi

import androidx.multidex.MultiDexApplication
import com.geanbrandao.testesicredi.module.adapterModule
import com.geanbrandao.testesicredi.module.repositoryModule
import com.geanbrandao.testesicredi.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOf(viewModelModule, repositoryModule, adapterModule))
        }

        if (BuildConfig.DEBUG) {
            // print log if is in debug mode
            Timber.plant(Timber.DebugTree())
        } else {
            // TODO create crash report using appCenter
        }
    }
}