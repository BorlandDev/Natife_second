package com.borlanddev.natife_second.di

import com.borlanddev.natife_second.api.endpoint.UserAPI
import com.borlanddev.natife_second.api.repository.NetworkSource
import com.borlanddev.natife_second.database.LocalSource
import com.borlanddev.natife_second.database.UserDao
import com.borlanddev.natife_second.database.UserDatabase
import com.borlanddev.natife_second.helpers.MainRepository
import com.borlanddev.natife_second.screens.list.ListFragment
import dagger.Component

@Component(
    dependencies = [
        DataComponent::class]
)
interface AppComponent {

    fun inject(listFragment: ListFragment)
}


@Component(
    modules = [
        DataModule::class,
        DataBindingsModule::class]
)
interface DataComponent {

    val mainRepository: MainRepository
    val localSource: LocalSource
    val networkSource: NetworkSource
    val userAPI: UserAPI
    val userDao: UserDao
    val userDatabase: UserDatabase
}
