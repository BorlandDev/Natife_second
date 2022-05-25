package com.borlanddev.natife_second.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.borlanddev.natife_second.helpers.DATABASE_NAME
import com.borlanddev.natife_second.model.UserDB

class UserDBRepository private constructor(context: Context) {

    private val database = Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        DATABASE_NAME
    )
        .build()

    private val userDao = database.userDao()

    fun getUsersDB(): LiveData<MutableList<UserDB>> = userDao.getUsersDB()

    fun getUserDB(id: String): LiveData<UserDB?> = userDao.getUserDB(id)

    companion object {
        private var INSTANCE: UserDBRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) INSTANCE = UserDBRepository(context)
        }

        fun get(): UserDBRepository {
            return INSTANCE ?: throw IllegalStateException("UserDBRepository must be initialized")
        }
    }
}