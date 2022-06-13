package com.borlanddev.natife_second.database

import com.borlanddev.natife_second.model.UserDB
import java.util.concurrent.Executors
import javax.inject.Inject

class UserDBRepository : LocalSource {

    @Inject
    lateinit var userDao: UserDao
    private val executor = Executors.newSingleThreadExecutor()

    override fun getUsersDB(limit: Int, offset: Int): List<UserDB> =
        userDao.getUsersDB(limit, offset)

    override fun getUserDB(id: String): UserDB =
        userDao.getUserDB(id)

    override fun addUsersDB(users: List<UserDB>): Unit =
        executor.execute { userDao.addUsersDB(users) }

    override fun clearDB(): Unit =
        executor.execute { userDao.clearDB() }

    companion object {
        private var INSTANCE: UserDBRepository? = null

        fun get(): UserDBRepository {
            return INSTANCE ?: throw IllegalStateException("UserDBRepository must be initialized")
        }
    }
}