package com.borlanddev.natife_second.di

import androidx.room.Room
import com.borlanddev.natife_second.database.UserDao
import com.borlanddev.natife_second.database.UserDatabase
import com.borlanddev.natife_second.helpers.DATABASE_NAME
import org.koin.dsl.module

val localModule = module {

    // or single ?
    factory<UserDao> {
        val database = Room.databaseBuilder(
            get(),
            UserDatabase::class.java,
            DATABASE_NAME
        ).build()

        database.userDao()
    }

}