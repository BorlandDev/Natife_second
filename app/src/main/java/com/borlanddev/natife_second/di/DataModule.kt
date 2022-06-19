package com.borlanddev.natife_second.di

import android.content.Context
import androidx.room.Room
import com.borlanddev.natife_second.api.endpoint.UserAPI
import com.borlanddev.natife_second.api.repository.NetworkRepository
import com.borlanddev.natife_second.api.repository.NetworkSource
import com.borlanddev.natife_second.database.LocalSource
import com.borlanddev.natife_second.database.UserDBRepository
import com.borlanddev.natife_second.database.UserDao
import com.borlanddev.natife_second.database.UserDatabase
import com.borlanddev.natife_second.helpers.BASE_URL
import com.borlanddev.natife_second.helpers.DATABASE_NAME
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class DataModule {

    @Provides
    fun provideRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideUserApi(retrofit: Retrofit): UserAPI = retrofit.create(UserAPI::class.java)

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
