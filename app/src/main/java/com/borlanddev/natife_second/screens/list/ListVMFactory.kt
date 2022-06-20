package com.borlanddev.natife_second.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ListVMFactory @Inject constructor(
    private val listVMProvider: Provider<ListVM>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return listVMProvider.get() as T
    }
}
