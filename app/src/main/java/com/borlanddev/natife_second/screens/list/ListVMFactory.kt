package com.borlanddev.natife_second.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.borlanddev.natife_second.helpers.MainRepository

class ListVMFactory(
    private val mainRepository: MainRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ListVM(mainRepository) as T
    }
}


