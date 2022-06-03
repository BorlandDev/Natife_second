package com.borlanddev.natife_second.screens.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borlanddev.natife_second.helpers.MainRepository
import com.borlanddev.natife_second.model.UserDB
import kotlin.concurrent.thread

class DetailsVM(val id: String) : ViewModel() {
    private val mainRepository = MainRepository.getInstance()
    private val _getUserLiveData = MutableLiveData<UserDB>()
    val getUserLiveDAta = _getUserLiveData

    fun getUser() {
        thread { _getUserLiveData.postValue(mainRepository.getUserDB(id)) }
    }
}