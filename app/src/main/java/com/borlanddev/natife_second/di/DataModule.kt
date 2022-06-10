package com.borlanddev.natife_second.di

import androidx.room.Room
import com.borlanddev.natife_second.api.endpoint.UserAPI
import com.borlanddev.natife_second.api.repository.NetworkRepository
import com.borlanddev.natife_second.api.repository.NetworkSource
import com.borlanddev.natife_second.api.retrofit.RetrofitClient
import com.borlanddev.natife_second.database.LocalSource
import com.borlanddev.natife_second.database.UserDBRepository
import com.borlanddev.natife_second.database.UserDao
import com.borlanddev.natife_second.database.UserDatabase
import com.borlanddev.natife_second.helpers.Application
import com.borlanddev.natife_second.helpers.DATABASE_NAME
import com.borlanddev.natife_second.helpers.MainRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideUserApi(retrofit: RetrofitClient): UserAPI = retrofit.userAPI

    @Provides
    fun provideDataBase(): UserDatabase {
        val context = Application().applicationContext

        return Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    fun provideUserDao(database: UserDatabase): UserDao = database.userDao()

    @Provides
    fun provideNetworkSource(): NetworkSource = NetworkRepository()

    @Provides
    fun provideLocalSource(): LocalSource = UserDBRepository.get()


  @Provides
  fun provideMainRepository(
      networkSource: NetworkSource,
      localSource: LocalSource
  ): MainRepository {
      return MainRepository(
          networkSource,
          localSource
      )
  }
}

@Module
interface DataBindingsModule {

    @Binds
    fun bindNetworkSource(networkRepository: NetworkRepository): NetworkSource

    @Binds
    fun bindLocalSource(localRepository: UserDBRepository): LocalSource

}