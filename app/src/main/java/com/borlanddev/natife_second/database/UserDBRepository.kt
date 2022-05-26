package com.borlanddev.natife_second.database

import android.content.Context
import androidx.room.Room
import com.borlanddev.natife_second.helpers.DATABASE_NAME
import com.borlanddev.natife_second.model.UserDB
import java.util.concurrent.Executors

class UserDBRepository private constructor(context: Context) {

    private val database = Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        DATABASE_NAME
    )
        .build()

    private val userDao = database.userDao()

    private val executor = Executors.newSingleThreadExecutor()

    fun getUsersDB(): List<UserDB> = userDao.getUsersDB()

    fun getUserDB(id: String): UserDB? = userDao.getUserDB(id)

    fun addUserDB(user: UserDB) = executor.execute { userDao.addUserDB(user) }

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