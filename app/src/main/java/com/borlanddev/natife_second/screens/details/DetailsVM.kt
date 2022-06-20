package com.borlanddev.natife_second.screens.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.borlanddev.natife_second.helpers.MainRepository
import com.borlanddev.natife_second.model.UserDB
import java.util.concurrent.Executors
import javax.inject.Inject

class DetailsVM @Inject constructor(
    private val id: String,
    private val mainRepository: MainRepository
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