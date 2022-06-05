package com.borlanddev.natife_second.api.repository

import com.borlanddev.natife_second.api.endpoint.UserAPI
import com.borlanddev.natife_second.api.retrofit.RetrofitClient
import com.borlanddev.natife_second.model.User
import com.borlanddev.natife_second.model.UserResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepository(private val userApi: UserAPI = RetrofitClient.userAPI) {

    fun getUsers(
        pageIndex: Int,
        pageSize: Int,
        onSuccess: (List<User>) -> Unit,
        onFailure: (msg: String) -> Unit
    ) {
        userApi.fetchUsers(pageIndex, pageSize).enqueue(object : Callback<UserResponse> {

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


