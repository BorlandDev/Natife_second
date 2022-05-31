package com.borlanddev.natife_second.screens.list

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borlanddev.natife_second.api.repository.Repository
import com.borlanddev.natife_second.database.UserDBRepository
import com.borlanddev.natife_second.helpers.LIMIT_USER
import com.borlanddev.natife_second.helpers.PAGE_SIZE
import com.borlanddev.natife_second.model.User
import com.borlanddev.natife_second.model.UserDB
import kotlin.concurrent.thread

class ListVM(
    private val userDBRepository: UserDBRepository,
    private val repository: Repository
) : ViewModel() {
    private val _userListLiveData = MutableLiveData<List<UserDB>>(listOf())
    val userListLiveData: LiveData<List<UserDB>> = _userListLiveData
    private var offset = 0
    private var accUsers: List<UserDB> = listOf()

    init {
        if (_userListLiveData.value?.isEmpty() == true)
            getUsers()
    }

    fun getUsers() {
        val pageIndex = (_userListLiveData.value?.size ?: 0) / PAGE_SIZE + 1
        repository.getUsers(
            pageIndex = pageIndex,
            results = PAGE_SIZE,
            {
                val users = it.map { user -> userToUserDB(user) }
                val currentUsers = _userListLiveData.value ?: emptyList()
                _userListLiveData.value = currentUsers + users
                if (pageIndex == 1) {
                    userDBRepository.clearDB()
                }
                userDBRepository.addUsersDB(users)
            },
            {
                Log.d(TAG, "FAILURE LOAD $it")

                thread {
                    val currentUsers = userDBRepository.getUsersDB(LIMIT_USER, offset)
                    accUsers = accUsers + currentUsers
                    _userListLiveData.postValue(accUsers)
                    offset += currentUsers.size
                }
            }
        )
    }

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