package com.borlanddev.natife_second.di

import com.borlanddev.natife_second.api.repository.NetworkRepository
import com.borlanddev.natife_second.api.repository.NetworkSource
import com.borlanddev.natife_second.database.LocalSource
import com.borlanddev.natife_second.database.UserDBRepository
import com.borlanddev.natife_second.helpers.MainRepository
import org.koin.dsl.module

val mainRepositoryModule = module {

    single<MainRepository> {
        MainRepository(
            networkSource = get(),
            localSource = get()
        )
    }

    // or single ?
    factory<NetworkSource> {
        NetworkRepository(
            userApi = get()
        )
    }

    // or single ?
    factory<LocalSource> {
        UserDBRepository(
            userDao = get()
        )
    }

}