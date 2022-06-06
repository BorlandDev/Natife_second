package com.borlanddev.natife_second.database

import com.borlanddev.natife_second.model.UserDB
import java.util.concurrent.Executors

class UserDBRepository private constructor(private val userDao: UserDao) {
    private val executor = Executors.newSingleThreadExecutor()

    fun getUsersDB(limit: Int, offset: Int): List<UserDB> = userDao.getUsersDB(limit, offset)

    fun getUserDB(id: String): UserDB = userDao.getUserDB(id)

    fun addUsersDB(users: List<UserDB>) = executor.execute { userDao.addUsersDB(users) }

    fun clearDB() = executor.execute { userDao.clearDB() }

    companion object {
        private var INSTANCE: UserDBRepository? = null

        fun initialize(userDao: UserDao) {
            if (INSTANCE == null) INSTANCE = UserDBRepository(userDao)
        }

        fun get(): UserDBRepository {
            return INSTANCE ?: throw IllegalStateException("UserDBRepository must be initialized")
        }
    }
}