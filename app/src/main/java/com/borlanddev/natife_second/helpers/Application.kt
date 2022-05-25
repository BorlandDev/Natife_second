package com.borlanddev.natife_second.helpers

import android.app.Application
import com.borlanddev.natife_second.database.UserDBRepository

class Application: Application() {

    override fun onCreate() {
        super.onCreate()
        UserDBRepository.initialize(this)
    }
}