package com.borlanddev.natife_second.helpers

import android.content.ContentValues
import android.util.Log
import com.borlanddev.natife_second.api.repository.NetworkRepository
import com.borlanddev.natife_second.database.UserDBRepository
import com.borlanddev.natife_second.model.User
import com.borlanddev.natife_second.model.UserDB
import kotlin.concurrent.thread

interface Repository {

    fun getUsersFromMainRepository(
        pageIndex: Int,
        offset: Int,
        onSuccess: (List<UserDB>) -> Unit,
        onFailure: (List<UserDB>) -> Unit
    )

    fun getUsersDB(offset: Int): List<UserDB>

    fun getUserDB(id: String): UserDB?
}

class MainRepository private constructor(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: UserDBRepository,
) : Repository {

    override fun getUsersFromMainRepository(
        pageIndex: Int,
        offset: Int,
        onSuccess: (List<UserDB>) -> Unit,
        onFailure: (List<UserDB>) -> Unit
    ) {
        networkRepository.getUsers(
            pageIndex,
            PAGE_SIZE,
            {
                val users = it.map { user -> userToUserDB(user) }
                if (pageIndex == 1) {
                    databaseRepository.clearDB()
                }
                databaseRepository.addUsersDB(users)
                onSuccess(users)
            }, {
                Log.d(ContentValues.TAG, "FAILURE LOAD $it")

                thread {
                    val users = getUsersDB(offset)
                    onFailure(users)
                }
            })
    }

    override fun getUsersDB(offset: Int): List<UserDB> =
        databaseRepository.getUsersDB(PAGE_SIZE, offset)

    override fun getUserDB(id: String): UserDB? = databaseRepository.getUserDB(id)

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

