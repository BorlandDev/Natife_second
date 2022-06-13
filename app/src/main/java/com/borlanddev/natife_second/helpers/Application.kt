package com.borlanddev.natife_second.helpers

import android.app.Application
import android.content.Context
import com.borlanddev.natife_second.database.UserDatabase
import com.borlanddev.natife_second.di.AppComponent
import com.borlanddev.natife_second.di.DaggerAppComponent
import com.borlanddev.natife_second.di.DaggerDataComponent
import javax.inject.Inject

class Application : Application() {

    lateinit var appComponent: AppComponent
        private set

    @Inject
    lateinit var database: UserDatabase

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .dataComponent(
                DaggerDataComponent.builder()
                    .context(this)
                    .build()
            )
            .build()
    }
}


val Context.appComponent: AppComponent
    get() = this.applicationContext.appComponent
