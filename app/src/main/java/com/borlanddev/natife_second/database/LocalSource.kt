package com.borlanddev.natife_second.database

import com.borlanddev.natife_second.model.UserDB

interface LocalSource {

    fun getUsersDB(limit: Int, offset: Int): List<UserDB>

    fun getUserDB(id: String): UserDB

    fun addUsersDB(users: List<UserDB>): Unit

    fun clearDB(): Unit
}