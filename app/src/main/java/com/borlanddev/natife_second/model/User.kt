package com.borlanddev.natife_second.model

import androidx.room.PrimaryKey

data class User(
    @PrimaryKey var id: Int = 0,
    var name: String = "",
    var email: String = "",
    var location: String = "",
    var phone: String = "",
    var password: String = ""
)