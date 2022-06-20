package com.borlanddev.natife_second.di

import android.content.Context
import com.borlanddev.natife_second.screens.details.DetailsFragment
import com.borlanddev.natife_second.screens.list.ListFragment
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

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance context: Context
        ): AppComponent
    }
}


