package com.borlanddev.natife_second.database

import android.content.Context
import androidx.room.Room
import com.borlanddev.natife_second.helpers.DATABASE_NAME

class UserDBRepository (context: Context){

    private val database = Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        DATABASE_NAME)
        .build()

}