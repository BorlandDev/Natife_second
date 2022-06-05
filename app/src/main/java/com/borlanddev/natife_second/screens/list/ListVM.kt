package com.borlanddev.natife_second.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borlanddev.natife_second.helpers.MainRepository
import com.borlanddev.natife_second.helpers.PAGE_SIZE
import com.borlanddev.natife_second.model.UserDB

class ListVM : ViewModel() {
    private val _userListLiveData = MutableLiveData<List<UserDB>>(listOf())
    val userListLiveData: LiveData<List<UserDB>> = _userListLiveData
    private val mainRepository = MainRepository.getInstance()
    private val pageIndex = (_userListLiveData.value?.size ?: 0) / PAGE_SIZE + 1
    private var offset = 0

    init {
        getUsers()
    }

    fun getUsers() {
        mainRepository.getUsersFromMainRepository(
            pageIndex,
            offset,
            {
                val currentUsers = _userListLiveData.value ?: emptyList()
                _userListLiveData.value = currentUsers + it
            }, {
                _userListLiveData.postValue(it)
                // обновить offset
            })
    }

}