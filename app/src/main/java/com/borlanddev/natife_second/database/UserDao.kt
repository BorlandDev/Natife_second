package com.borlanddev.natife_second.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.borlanddev.natife_second.model.UserDB

@Dao
interface UserDao {

    @Query("SELECT * FROM UserDB")
    fun getUsersDB(): LiveData<MutableList<UserDB>>

    @Query("SELECT * FROM UserDB WHERE id=(:id)")
    fun getUserDB(id: String): LiveData<UserDB?>
}