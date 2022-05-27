package com.borlanddev.natife_second.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.borlanddev.natife_second.database.UserDBRepository

class DetailsVMFactory(
    private val userDBRepository: UserDBRepository,
    private val id: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsVM(userDBRepository, id) as T
    }
}

