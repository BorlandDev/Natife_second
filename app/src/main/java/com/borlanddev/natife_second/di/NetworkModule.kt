package com.borlanddev.natife_second.di

import com.borlanddev.natife_second.api.endpoint.UserAPI
import com.borlanddev.natife_second.helpers.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single<UserAPI> {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(UserAPI::class.java)
    }
}