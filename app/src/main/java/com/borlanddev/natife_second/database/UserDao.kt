package com.borlanddev.natife_second.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.borlanddev.natife_second.model.UserDB

@Dao
interface UserDao {

    @Query(
        "SELECT * FROM UserDB " +
                "LIMIT :limit OFFSET :offset "
    )
    fun getUsersDB(limit: Int, offset: Int): List<UserDB>

    @Query("SELECT * FROM UserDB WHERE id=(:id)")
    fun getUserDB(id: String): UserDB

    @Insert
    fun addUsersDB(users: List<UserDB>)

    @Query("DELETE FROM UserDB")
    fun clearDB()
}