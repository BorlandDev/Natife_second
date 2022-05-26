package com.borlanddev.natife_second.screens.list

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borlanddev.natife_second.api.repository.Repository
import com.borlanddev.natife_second.database.UserDBRepository
import com.borlanddev.natife_second.model.User
import com.borlanddev.natife_second.model.UserDB
import kotlin.concurrent.thread

class ListVM : ViewModel() {
    private val repository = Repository()
    private val userDBRepository = UserDBRepository.get()
    private val _userListLiveData = MutableLiveData<List<UserDB>>(listOf())
    val userListLiveData: LiveData<List<UserDB>> = _userListLiveData

    init {
        if (_userListLiveData.value?.isEmpty() == true)
            getUsers()
    }

    private fun getUsers() {
        repository.getUsers(results = 20,
            {
                val users = it.map { user -> userToUserDB(user) }
                users.map { user -> userDBRepository.addUserDB(user) }
                _userListLiveData.value = users
            },
            {
                Log.d(TAG, "FAILURE LOAD $it")

                thread { _userListLiveData.postValue(userDBRepository.getUsersDB()) }
            }
        )
    }

    private fun userToUserDB(user: User): UserDB {
        val id = user.id?.uuid.toString()
        val (title, first, last) = user.name!!
        val name = "$title $first $last"
        val phone = user.phone.toString()
        val email = user.email.toString()
        val location =
            "${user.location?.city} \n ${user.location?.state} \n ${user.location?.county}"
        val picture = user.picture?.large.toString()
        val age = user.age?.age.toString()

        return UserDB(id, name, phone, email, location, picture, age)
    }
}