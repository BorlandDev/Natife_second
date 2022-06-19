package com.borlanddev.natife_second.api.repository

import com.borlanddev.natife_second.api.endpoint.UserAPI
import com.borlanddev.natife_second.model.User
import javax.inject.Inject

class NetworkRepository @Inject constructor(
    private val userApi: UserAPI
) : NetworkSource {

    override fun getUsers(
        pageIndex: Int,
        pageSize: Int
    ): Result<List<User>> {
        return try {
            val response = userApi.fetchUsers(pageIndex, pageSize).execute()
            if (response.isSuccessful) {
                Result.success(response.body()?.results ?: listOf())
            } else {
                //TODO Parse error body
                Result.failure(RuntimeException("Received response code ${response.code()}"))
            }
        } catch (exc: Exception) {
            Result.failure(exc)
        }
    }
}