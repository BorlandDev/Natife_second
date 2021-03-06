package com.borlanddev.natife_second.api.endpoint

import com.borlanddev.natife_second.model.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserAPI {

    @GET("api/")
    fun fetchUsers(
        @Query("page") pageIndex: Int,
        @Query("results") pageSize: Int
    ): Call<UserResponse>
}