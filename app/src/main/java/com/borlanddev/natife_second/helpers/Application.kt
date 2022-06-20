package com.borlanddev.natife_second.helpers

import android.app.Application
import android.content.Context
import com.borlanddev.natife_second.di.AppComponent
import com.borlanddev.natife_second.di.DaggerAppComponent

class Application : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .factory()
            .create(context = this)
    }
}

val Context.appComponent: AppComponent
    get() = (this.applicationContext as com.borlanddev.natife_second.helpers.Application).appComponent