package com.borlanddev.natife_second.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDB(
    @PrimaryKey val id: String,
    val name: String,
    val phone: String,
    val email: String,
    val location: String,
    val picture: String,
    val age: String
)