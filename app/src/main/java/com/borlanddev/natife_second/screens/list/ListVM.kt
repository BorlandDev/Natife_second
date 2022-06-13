package com.borlanddev.natife_second.screens.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borlanddev.natife_second.helpers.MainRepository
import com.borlanddev.natife_second.helpers.PAGE_SIZE
import com.borlanddev.natife_second.model.UserDB
import javax.inject.Inject

class ListVM @Inject constructor(
    val mainRepository: MainRepository
) : ViewModel() {
    private val _userListLiveData = MutableLiveData<List<UserDB>>(listOf())
    val userListLiveData: LiveData<List<UserDB>> = _userListLiveData
    private var offset = 0
    private val pageIndex: Int
        get() = (_userListLiveData.value?.size ?: 0) / PAGE_SIZE + 1

    init {
        getUsers()
    }

    fun getUsers() {
        mainRepository.getUsers(
            pageIndex,
            offset
        ) {
            val currentUsers = _userListLiveData.value ?: emptyList()
            _userListLiveData.postValue(currentUsers + it)
            offset += it.size
        }
    }
}