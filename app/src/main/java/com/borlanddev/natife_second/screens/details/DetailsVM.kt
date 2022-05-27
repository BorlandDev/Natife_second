package com.borlanddev.natife_second.screens.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borlanddev.natife_second.database.UserDBRepository
import com.borlanddev.natife_second.model.UserDB
import kotlin.concurrent.thread

class DetailsVM(private val userDBRepository: UserDBRepository, val id: String) : ViewModel() {

    private val _getUserLiveData = MutableLiveData<UserDB>()
    val getUserLiveDAta = _getUserLiveData

    fun getUser() {
        thread { _getUserLiveData.postValue(userDBRepository.getUserDB(id)) }
    }
}