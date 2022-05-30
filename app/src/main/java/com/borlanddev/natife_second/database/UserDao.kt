package com.borlanddev.natife_second.database

import androidx.room.*
import com.borlanddev.natife_second.helpers.DATABASE_NAME
import com.borlanddev.natife_second.model.UserDB

@Dao
interface UserDao {

    @Query("SELECT * FROM UserDB")
    fun getUsersDB(): List<UserDB>

    @Query("SELECT * FROM UserDB WHERE id=(:id)")
    fun getUserDB(id: String): UserDB?

    @Insert
    fun addUsersDB(users: List<UserDB>)

    @Query("DELETE FROM UserDB")
    fun clearDB()
}