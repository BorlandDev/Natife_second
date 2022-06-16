package com.borlanddev.natife_second.helpers

import android.app.Application
import com.borlanddev.natife_second.di.localModule
import com.borlanddev.natife_second.di.mainRepositoryModule
import com.borlanddev.natife_second.di.networkModule
import com.borlanddev.natife_second.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@Application)
            modules(
                listOf(
                    mainRepositoryModule,
                    networkModule,
                    localModule,
                    viewModelModule
                )
            )
        }
    }
}