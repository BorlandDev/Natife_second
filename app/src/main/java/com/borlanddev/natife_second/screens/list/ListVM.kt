package com.borlanddev.natife_second.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borlanddev.natife_second.helpers.MainRepository
import com.borlanddev.natife_second.helpers.PAGE_SIZE
import com.borlanddev.natife_second.model.UserDB

class ListVM : ViewModel() {
    private val _userListLiveData = MutableLiveData<List<UserDB>>(listOf())
    private val pageIndex = (_userListLiveData.value?.size ?: 0) / PAGE_SIZE + 1
    private val currentUsers = _userListLiveData.value ?: emptyList()
    private val mainRepository = MainRepository.getInstance()
    val userListLiveData: LiveData<List<UserDB>> = _userListLiveData

    init {
        getUserFromRepository()
    }

    fun getUserFromRepository() {
        val res = mainRepository.getUsers(pageIndex, currentUsers)

        if (res == "Net") {
            _userListLiveData.value = mainRepository.resultUsersFromNetwork
        } else {
            _userListLiveData.postValue(mainRepository.resultUsersFromDB)
        }
    }

}