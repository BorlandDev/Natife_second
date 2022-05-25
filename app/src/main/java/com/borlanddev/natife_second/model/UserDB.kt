package com.borlanddev.natife_second.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserDB(
    @PrimaryKey val id: Int,
//    val userName: UserName,
//    val userEmail: UserEmail,
//    val userPhone: UserPhone,
//    val userPicture: UserPicture
)