package com.borlanddev.natife_second.di

import android.content.Context
import com.borlanddev.natife_second.api.repository.NetworkSource
import com.borlanddev.natife_second.database.LocalSource
import com.borlanddev.natife_second.screens.details.DetailsFragment
import com.borlanddev.natife_second.screens.list.ListFragment
import com.borlanddev.natife_second.screens.list.ListVMFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        DataModule::class,
        DataBindingsModule::class
    ]
)
@Singleton
interface AppComponent {

    fun inject(listFragment: ListFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun listVMFactory(): ListVMFactory
    fun networkSource(): NetworkSource
    fun localSource(): LocalSource

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}


