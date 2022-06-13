package com.borlanddev.natife_second.di

import android.content.Context
import com.borlanddev.natife_second.api.repository.NetworkSource
import com.borlanddev.natife_second.database.LocalSource
import com.borlanddev.natife_second.screens.details.DetailsFragment
import com.borlanddev.natife_second.screens.list.ListFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [
        DataComponent::class]
)
interface AppComponent {

    fun inject(listFragment: ListFragment)
    fun inject(detailsFragment: DetailsFragment)
}

@Component(
    modules = [
        DataModule::class,
        DataBindingsModule::class
    ]
)
interface DataComponent {

    val networkSource: NetworkSource
    val localSource: LocalSource

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): DataComponent
    }
}

