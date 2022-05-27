package com.borlanddev.natife_second.database

import com.borlanddev.natife_second.model.UserDB
import java.util.concurrent.Executors

class UserDBRepository private constructor(_database: UserDatabase) {
    private val database = _database
    private val userDao = database.userDao()
    private val executor = Executors.newSingleThreadExecutor()

    fun getUsersDB(): List<UserDB> = userDao.getUsersDB()

    fun getUserDB(id: String): UserDB? = userDao.getUserDB(id)

    fun addUsersDB(users: List<UserDB>) = executor.execute { userDao.addUsersDB(users) }

    companion object {
        private var INSTANCE: UserDBRepository? = null

        fun initialize(_database: UserDatabase) {
            if (INSTANCE == null) INSTANCE = UserDBRepository(_database)
        }

        fun get(): UserDBRepository {
            return INSTANCE ?: throw IllegalStateException("UserDBRepository must be initialized")
        }
    }
}