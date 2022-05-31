package com.borlanddev.natife_second.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.borlanddev.natife_second.api.repository.Repository
import com.borlanddev.natife_second.database.UserDBRepository

class ListVMFactory(
    private val userDBRepository: UserDBRepository,
    private val repository: Repository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListVM(userDBRepository, repository) as T
    }
}

fun factory() = ListVMFactory(UserDBRepository.get(), Repository())
