package com.borlanddev.natife_second.screens.list

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borlanddev.natife_second.api.repository.Repository
import com.borlanddev.natife_second.model.User

class ListVM : ViewModel() {
    private val repository = Repository()
    private val _userListLiveData = MutableLiveData<List<User>>(listOf())
    val userListLiveData: LiveData<List<User>> = _userListLiveData

    init {
        if (_userListLiveData.value?.isEmpty() == true) {
            getUsers()
        }
    }

    private fun getUsers() {
        repository.getUsers(results = 20,
            {
                _userListLiveData.value = it
            },
            {
                Log.d(TAG, "FAILURE LOAD $it")
            }
        )
    }

}