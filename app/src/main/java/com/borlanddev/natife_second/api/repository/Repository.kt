package com.borlanddev.natife_second.api.repository

import com.borlanddev.natife_second.api.endpoint.UserAPI
import com.borlanddev.natife_second.api.retrofit.RetrofitClient
import com.borlanddev.natife_second.model.User
import com.borlanddev.natife_second.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Repository(val userApi: UserAPI = RetrofitClient.userAPI) {

    fun getUsers(
        results: Int,
        onSuccess: (List<User>) -> Unit,
        onFailure: (msg: String) -> Unit,
    ) {
        userApi.fetchUsers(results).enqueue(object : Callback<UserResponse> {

            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                if (response.isSuccessful) {
                    onSuccess(response.body()?.results ?: listOf())
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                onFailure(t.localizedMessage ?: "")
            }
        })
    }
}


