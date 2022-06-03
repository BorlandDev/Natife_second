package com.borlanddev.natife_second.helpers

import android.content.ContentValues
import android.util.Log
import com.borlanddev.natife_second.api.repository.NetworkRepository
import com.borlanddev.natife_second.database.UserDBRepository
import com.borlanddev.natife_second.model.User
import com.borlanddev.natife_second.model.UserDB
import kotlin.concurrent.thread

interface Repository {
    fun getUsers(
        pageIndex: Int,
        currentUsers: List<UserDB>
    ): String

    fun getUserDB(id: String): UserDB?
}

class MainRepository private constructor(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: UserDBRepository,
) : Repository {

    private var offset = 0
    private var accUsers: List<UserDB> = listOf()
    var resultUsersFromNetwork: List<UserDB> = listOf()
    var resultUsersFromDB: List<UserDB> = listOf()
    var flag = ""

    override fun getUsers(
        pageIndex: Int,
        currentUsers: List<UserDB>
    ): String {
        networkRepository.getUsers(
            pageIndex = pageIndex,
            results = PAGE_SIZE,
            {
                val users = it.map { user -> userToUserDB(user) }

                if (pageIndex == 1) {
                    databaseRepository.clearDB()
                }
                databaseRepository.addUsersDB(users)
                flag = "Net"
                resultUsersFromNetwork = resultUsersFromNetwork + (currentUsers + users)
            }
        ) {
            Log.d(ContentValues.TAG, "FAILURE LOAD $it")
            thread {
                val currentUsersFailure = databaseRepository.getUsersDB(PAGE_SIZE, offset)
                offset += currentUsers.size
                flag = "DB"
                resultUsersFromDB = resultUsersFromDB + (accUsers + currentUsersFailure)
            }
        }
    return flag
    }

    override fun getUserDB(id: String) = databaseRepository.getUserDB(id)

    private fun userToUserDB(user: User) = UserDB(
        id = user.id?.uuid.toString(),
        phone = user.phone.toString(),
        email = user.email.toString(),
        age = user.age?.age.toString(),
        picture = user.picture?.large.toString(),
        name = "${user.name?.title} ${user.name?.first} ${user.name?.last}",
        location = " ${user.location?.country} \n ${user.location?.state} \n ${user.location?.city}"
    )

    companion object {
        @Volatile
        private var INSTANCE: MainRepository? = null

        fun getInstance() = INSTANCE ?: synchronized(this) {
            INSTANCE ?: MainRepository(
                NetworkRepository(),
                UserDBRepository.get()
            ).also { INSTANCE = it }
        }
    }
}

