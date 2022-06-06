package com.borlanddev.natife_second.helpers

import android.content.ContentValues
import android.util.Log
import com.borlanddev.natife_second.api.repository.NetworkRepository
import com.borlanddev.natife_second.database.UserDBRepository
import com.borlanddev.natife_second.model.User
import com.borlanddev.natife_second.model.UserDB
import kotlin.concurrent.thread

class MainRepository private constructor(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: UserDBRepository,
) {
    fun getUsers(
        pageIndex: Int,
        offset: Int,
        result: (List<UserDB>) -> Unit
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
                result(users)
            }, {
                Log.d(ContentValues.TAG, "FAILURE LOAD $it")
                thread { result(databaseRepository.getUsersDB(PAGE_SIZE, offset)) }
            })
    }

    fun getUser(
        id: String,
        result: (UserDB) -> Unit
    ) = thread { result(databaseRepository.getUserDB(id)) }

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


