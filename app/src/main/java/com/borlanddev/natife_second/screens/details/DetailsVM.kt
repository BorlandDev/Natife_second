package com.borlanddev.natife_second.screens.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borlanddev.natife_second.helpers.MainRepository
import com.borlanddev.natife_second.model.UserDB

class DetailsVM(
    private val mainRepository: MainRepository
) : ViewModel() {
    private val _getUserLiveData = MutableLiveData<UserDB>()
    val getUserLiveDAta = _getUserLiveData

    lateinit var id: String

    fun getUser() {
        mainRepository.getUser(id) {
            _getUserLiveData.postValue(it)
        }
    }
}