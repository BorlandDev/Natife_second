package com.borlanddev.natife_second.helpers

import android.app.Application
import androidx.room.Room
import com.borlanddev.natife_second.database.UserDBRepository
import com.borlanddev.natife_second.database.UserDatabase

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        val database = Room.databaseBuilder(
            this,
            UserDatabase::class.java,
            DATABASE_NAME
        )
            .build()

        UserDBRepository.initialize(database)
    }
}