package com.borlanddev.natife_second.screens.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borlanddev.natife_second.helpers.MainRepository
import com.borlanddev.natife_second.helpers.PAGE_SIZE
import com.borlanddev.natife_second.model.UserDB
import java.util.concurrent.Executors
import javax.inject.Inject

class ListVM @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _userListLiveData = MutableLiveData<List<UserDB>>(listOf())
    val userListLiveData: LiveData<List<UserDB>> = _userListLiveData

    @Volatile
    private var offset = 0
    private val pageIndex: Int
        get() = (_userListLiveData.value?.size ?: 0) / PAGE_SIZE + 1

    private val pageExecutor = Executors.newSingleThreadExecutor()

    @Volatile
    private var isPageLoading = false

    init {
        getUsers()
    }

    fun getUsers() {
        if (isPageLoading) {
            return
        }
        isPageLoading = true
        pageExecutor.execute {
            mainRepository.getUsers(pageIndex, offset)
                .fold(onSuccess = {
                    isPageLoading = false
                    val currentUsers = _userListLiveData.value ?: emptyList()
                    _userListLiveData.postValue(currentUsers + it)
                    offset += it.size
                }, onFailure = {
                    isPageLoading = false
                    Log.e("ListVM", "Error", it)
                    //TODO show error message
                })

        }
    }

    override fun onCleared() {
        super.onCleared()
        pageExecutor.shutdownNow()
    }

}