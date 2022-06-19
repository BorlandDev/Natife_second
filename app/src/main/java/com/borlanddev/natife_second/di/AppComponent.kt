package com.borlanddev.natife_second.di

import android.content.Context
import com.borlanddev.natife_second.api.repository.NetworkSource
import com.borlanddev.natife_second.database.LocalSource
import com.borlanddev.natife_second.screens.details.DetailsFragment
import com.borlanddev.natife_second.screens.list.ListFragment
import com.borlanddev.natife_second.screens.list.ListVMFactory
import dagger.BindsInstance
import dagger.Component

// Нужеы ли тут Scope и SubComponents ? А так же разбить комопоненты на отдельные файлы ?

@Component(
    dependencies = [
        DataComponent::class]
)
interface AppComponent {

    fun inject(listFragment: ListFragment)
    fun inject(detailsFragment: DetailsFragment)
    fun listVMFactory(): ListVMFactory

}

@Component(
    modules = [
        DataModule::class,
        DataBindingsModule::class,
    ]
)
interface DataComponent {

    val networkSource: NetworkSource
    val localSource: LocalSource

    // or Factory ?
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): DataComponent
    }
}

