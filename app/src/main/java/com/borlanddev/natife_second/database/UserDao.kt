package com.borlanddev.natife_second.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.borlanddev.natife_second.model.UserDB

@Dao
interface UserDao {

    @Query("SELECT * FROM UserDB")
    fun getUsersDB(): List<UserDB>

    @Query("SELECT * FROM UserDB WHERE id=(:id)")
    fun getUserDB(id: String): UserDB?

    @Insert
    fun addUserDB(user: UserDB)
}