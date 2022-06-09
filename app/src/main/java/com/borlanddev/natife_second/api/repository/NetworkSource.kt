package com.borlanddev.natife_second.api.repository

import com.borlanddev.natife_second.model.User

interface NetworkSource {

    fun getUsers(
        pageIndex: Int,
        pageSize: Int,
        onSuccess: (List<User>) -> Unit,
        onFailure: (msg: String) -> Unit
    )
}