package com.borlanddev.natife_second.helpers

import android.app.Application
import com.borlanddev.natife_second.database.UserDBRepository
import com.borlanddev.natife_second.database.UserDatabase
import com.borlanddev.natife_second.di.AppComponent
import com.borlanddev.natife_second.di.DaggerAppComponent

class Application : Application() {

    lateinit var appComponent: AppComponent
    lateinit var database: UserDatabase

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
           .dataComponent(DaggerDataComponent.create())
           .build()

        UserDBRepository.initialize()
    }
}

//
//val Context.appComponent: AppComponent
//    get() = when (this) {
//        is com.borlanddev.natife_second.helpers.Application -> appComponent
//        else -> this.applicationContext.appComponent
//    }