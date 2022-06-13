package com.borlanddev.natife_second.api.retrofit

import com.borlanddev.natife_second.api.endpoint.UserAPI
import com.borlanddev.natife_second.helpers.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // сделать DI ?
    private var retrofit: Retrofit? = null

    val userAPI: UserAPI
        get() = getClient().create(UserAPI::class.java)

    private fun getClient(): Retrofit {
        if (retrofit == null) {

            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}