package com.borlanddev.natife_second.screens.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borlanddev.natife_second.helpers.MainRepository
import com.borlanddev.natife_second.model.UserDB
import java.util.concurrent.Executors

class DetailsVM(
    private val mainRepository: MainRepository,
    private val id: String
) : ViewModel() {
    private val executor = Executors.newSingleThreadExecutor()
    private val _getUserLiveData = MutableLiveData<UserDB>()
    val getUserLiveDAta = _getUserLiveData

    fun getUser() {
        executor.execute {
            _getUserLiveData.postValue(
                mainRepository.getUser(id)
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        executor.shutdownNow()
    }
}