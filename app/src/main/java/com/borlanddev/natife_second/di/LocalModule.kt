package com.borlanddev.natife_second.di

import androidx.room.Room
import com.borlanddev.natife_second.database.UserDatabase
import com.borlanddev.natife_second.helpers.DATABASE_NAME
import org.koin.dsl.module

val localModule = module {

    single {
        Room.databaseBuilder(
            get(),
            UserDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    factory {
        get<UserDatabase>().userDao()
    }

}