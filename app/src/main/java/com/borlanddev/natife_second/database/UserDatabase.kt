package com.borlanddev.natife_second.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.borlanddev.natife_second.model.UserDB

@Database(entities = [UserDB::class], version = 1)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao() : UserDao
}