package com.borlanddev.natife_second.di

import android.content.Context
import androidx.room.Room
import com.borlanddev.natife_second.api.endpoint.UserAPI
import com.borlanddev.natife_second.api.repository.NetworkRepository
import com.borlanddev.natife_second.api.repository.NetworkSource
import com.borlanddev.natife_second.api.retrofit.RetrofitClient
import com.borlanddev.natife_second.database.LocalSource
import com.borlanddev.natife_second.database.UserDBRepository
import com.borlanddev.natife_second.database.UserDao
import com.borlanddev.natife_second.database.UserDatabase
import com.borlanddev.natife_second.helpers.DATABASE_NAME
import com.borlanddev.natife_second.helpers.MainRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module()
class DataModule() {

    @Provides
    fun provideNetworkSource(): NetworkRepository = NetworkRepository()

    @Provides
    fun provideUserApi(): UserAPI = RetrofitClient.userAPI

    @Provides
    fun provideUserDBRepository(): UserDBRepository = UserDBRepository.get()

    @Provides
    fun provideDataBase(context: Context): UserDatabase {
        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(database: UserDatabase): UserDao = database.userDao()
}

@Module
interface DataBindingsModule {

    @Binds
    fun bindNetworkSource(networkRepository: NetworkRepository): NetworkSource

    @Binds
    fun bindLocalSource(userDBRepository: UserDBRepository): LocalSource
}



