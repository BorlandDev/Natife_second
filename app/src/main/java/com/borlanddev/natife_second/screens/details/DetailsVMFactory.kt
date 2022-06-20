package com.borlanddev.natife_second.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.borlanddev.natife_second.helpers.MainRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DetailsVMFactory @AssistedInject constructor(
    @Assisted("userId") private val id: String,
    private val mainRepository: MainRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DetailsVM(id, mainRepository) as T
    }

    @AssistedFactory
    interface Factory {

        fun create(@Assisted("userId") id: String): DetailsVMFactory
    }
}