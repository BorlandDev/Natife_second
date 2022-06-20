package com.borlanddev.natife_second.di

import com.borlanddev.natife_second.screens.details.DetailsVM
import com.borlanddev.natife_second.screens.list.ListVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {

    viewModel {
        ListVM(
            mainRepository = get()
        )
    }

    viewModel { params ->
        DetailsVM(
            mainRepository = get(),
            id = params.get()
        )
    }
}