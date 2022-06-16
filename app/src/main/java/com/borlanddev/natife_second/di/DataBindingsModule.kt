package com.borlanddev.natife_second.di

import com.borlanddev.natife_second.api.repository.NetworkRepository
import com.borlanddev.natife_second.api.repository.NetworkSource
import com.borlanddev.natife_second.database.LocalSource
import com.borlanddev.natife_second.database.UserDBRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DataBindingsModule {

    @Binds
    fun bindNetworkSource(networkRepository: NetworkRepository): NetworkSource

    @Binds
    fun bindLocalSource(userDBRepository: UserDBRepository): LocalSource
}