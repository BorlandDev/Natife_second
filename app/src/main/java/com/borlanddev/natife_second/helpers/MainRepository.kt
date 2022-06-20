package com.borlanddev.natife_second.helpers

import com.borlanddev.natife_second.api.repository.NetworkSource
import com.borlanddev.natife_second.database.LocalSource
import com.borlanddev.natife_second.model.User
import com.borlanddev.natife_second.model.UserDB
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.concurrent.thread

@Singleton
class MainRepository @Inject constructor(
    private val networkSource: NetworkSource,
    private val localSource: LocalSource,
) {

    fun getUsers(pageIndex: Int, offset: Int): Result<List<UserDB>> {
        return try {
            val result = networkSource.getUsers(pageIndex, PAGE_SIZE)
            val resultList = result.getOrNull()
            return if (resultList != null) {
                val dbList = resultList.map { user -> userToUserDB(user) }
                if (pageIndex == 1) {
                    localSource.clearDB()
                }
                localSource.addUsersDB(dbList)
                Result.success(dbList)
            } else {
                Result.success(localSource.getUsersDB(PAGE_SIZE, offset))
            }
        } catch (exc: Exception) {
            Result.failure(exc)
        }
    }

    fun getUser(id: String): UserDB  = localSource.getUserDB(id)

    private fun userToUserDB(user: User) = UserDB(
        id = user.id?.uuid.toString(),
        phone = user.phone.toString(),
        email = user.email.toString(),
        age = user.age?.age.toString(),
        picture = user.picture?.large.toString(),
        name = "${user.name?.title} ${user.name?.first} ${user.name?.last}",
        location = " ${user.location?.country} \n ${user.location?.state} \n ${user.location?.city}"
    )
}


